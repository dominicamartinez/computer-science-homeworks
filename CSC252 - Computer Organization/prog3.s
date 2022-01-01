.data
names:   .word   name01
         .word   name02
         .word   name03
         .word   name04
         .word   0
name01:  .asciiz "El Paso & Southwestern"
name02:  .asciiz "Atchison, Topeka & Santa Fe"
name03:  .asciiz "New Mexico & Arizona"
name04:  .asciiz "Union Pacific"

letters: .asciiz "A"
         .asciiz "B"
         .asciiz "C"
         .asciiz "D"
         .asciiz "E"
         .asciiz "F"
         .asciiz "G"
         .asciiz "H"
         .asciiz "I"
         .asciiz "J"
         .asciiz "K"
         .asciiz "L"
         .asciiz "M"
         .asciiz "N"
         .asciiz "O"
         .asciiz "P"
         .asciiz "Q"
         .asciiz "R"
         .asciiz "S"
         .asciiz "T"
         .asciiz "U"
         .asciiz "V"
         .asciiz "W"
         .asciiz "X"
         .asciiz "Y"
         .asciiz "Z"

         .align  4
counts:  .space  104   # room for 26 integers (4 * 26 = 104 bytes)

newline: .asciiz "\n"
tab:     .asciiz "\t"

.text
main:
        # Function prologue -- even main has one
        subu  $sp, $sp, 24    # allocate stack space -- default of 24 here
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        addiu $fp, $sp, 24    # setup main's frame pointer

        # body of main

        la    $a0, names
        la    $a1, counts
        jal   letterCount

        la    $a0, counts
        jal   printCounts

done:   # Epilogue for main -- restore stack & frame pointers and return
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code


convert:
        # Function prologue
        subu  $sp, $sp, 24    # allocate stack space -- default of 24 here
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        sw    $a0, 8($sp)     # save contents of $a0
        sw    $a1, 12($sp)    # save contents of $a1
        addiu $fp, $sp, 24    # setup convert's frame pointer

        lb    $t1, 0($a0)     # $t1 = 1st byte of name

convertLoopBegin:
        beq   $t1, $zero, convertDone
        bne   $a1, $zero, convertToUpper

convertToLower:
        # if 'A' <= name[i] <= 'Z'
        li    $t2, 'A'
        slt   $t3, $t1, $t2
        bne   $t3, $zero, convertLoopEnd
        li    $t2, 'Z'
        slt   $t3, $t2, $t1
        bne   $t3, $zero, convertLoopEnd
        addi  $t1, $t1, 32     # convert to lower case
        sb    $t1, 0($a0)      # save changed character
        j     convertLoopEnd

convertToUpper:
        # if 'a' <= name[i] <= 'z'
        li    $t2, 'a'
        slt   $t3, $t1, $t2
        bne   $t3, $zero, convertLoopEnd
        li    $t2, 'z'
        slt   $t3, $t2, $t1
        bne   $t3, $zero, convertLoopEnd
        addi  $t1, $t1, -32    # convert to upper case
        sb    $t1, 0($a0)      # save changed character

convertLoopEnd:
        # get the next byte/character
        addi  $a0, $a0, 1
        lb    $t1, 0($a0)
        j     convertLoopBegin

convertDone:
        # Epilogue for convert -- restore stack & frame pointers & return
        lw    $a1, 12($sp)    # restore contents of $a1
        lw    $a0, 8($sp)     # restore contents of $a0
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code

# Your code goes below this line

# Dominic Martinez
# this program will go through a string and count up
# the number of times a character is encountered

letterCount:
    
        # Function prologue -- even main has one
        subu  $sp, $sp, 24    # allocate stack space -- default of 24 here
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        addiu $fp, $sp, 24    # setup main's frame pointer
        
	# save addr of names
	add $s0, $a0, $zero
	
	# save addr of counts
	add $s4, $a1, $zero
	
	# making this for multiplier later
	addi $s5, $zero, 4

loop:	
	# load the addr of the next string
        lw    $s1, 0($s0)
        
        # while ( current string != 0 )
        beq   $s1, $zero, Done

	# first parameter (the string)
	add $a0, $s1, $zero
	
	# second parameter (toUpperCase)
	addi $a1, $zero, 1
	
	# convert the strings
	jal convert
	
	# traverse the string and increment upon char
	#
   	# initiliazing index
     	add $s2, $zero, $zero

loop2:	

        # address of name[index]
        add $t7, $a0, $s2

     	# $s3 = ascii value of name[index]
     	lb $s3, ($t7)
     
     	# check to see if it is a null
     	beq $s3, $zero, end
     	
     	# check to see if it is any other
     	# character
     	slti $t3, $s3, 65
     	
     	bne $t3, $zero, poo
     	
     	addi $t4, $zero, 1
    	
    	slti $t3, $s3, 91
    	
    	bne $t3, $t4, poo
     	
     	# increment character occurrence
     	#
     	# subtract the ascii value of a
     	# in order to find its place on
     	# the array for incrementation
     	addi $t6, $s3, -65
     	
     	# this will find place in array
     	mul $t6, $t6, $s5
     	
     	# this will place it in memory
     	add $s4, $s4, $t6
     	
	# take value out
	lw $t5, ($s4)
	
	# increment
	addi $t5, $t5, 1
	
	# put value back in
	sw $t5, ($s4)
	
	# reset 
	sub $s4, $s4, $t6
	
poo:
     	# index++
     	add $s2, $s2, 1
     	
     	# go back up to continue 
     	b loop2

end:
        # next location in names array
        addi  $s0, $s0, 4
        
        j     loop
        
Done:
	# Epilogue for main -- restore stack & frame pointers and return
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code
	

printCounts:

        # Function prologue -- even main has one
        subu  $sp, $sp, 24    # allocate stack space -- default of 24 here
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        addiu $fp, $sp, 24    # setup main's frame pointer


	# traverse the integers now and print accordingly
	#
	# address of letters
	la $s0, letters
	
	# address of occurrences
	add $s1, $a0, $zero
	
boo:
	
	# print letter
	add $a0, $s0, $zero
	li    $v0, 4
        syscall
        
	# print tab
	la $a0, tab
        li    $v0, 4
        syscall
        
	# print occurrence
        lw $t1, ($s1) 
        add $a0, $t1, $zero
        li    $v0, 1
        syscall
        
	# print newline
        la $a0, newline
        li    $v0, 4
        syscall
        
        # check to make sure the letter is not Z
        lb $t0, ($s0)
        
        addi $t1, $t0, -90
        
        # when Z end
        beq $t1, $zero, ep
   
        # increment letters and counts
        addi $s1, $s1, 4
        
        addi $s0, $s0, 2
        
	b boo
	
ep:
	
	# Epilogue for main -- restore stack & frame pointers and return
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code
	