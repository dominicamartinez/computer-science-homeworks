.data
name01:  .asciiz "El Paso & Southwestern"
name02:  .asciiz "Atchison, Topeka & Santa Fe"
newline: .ascii "\n"

.text
main:
        # Function prologue -- even main has one
        subu  $sp, $sp, 24    # allocate stack space -- default of 24
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        addiu $fp, $sp, 24    # setup main's frame pointer

        # body of main

        # Rather simple, no loop used.  Just call convert with a string
        # and a 0 or 1 to indicate the desired conversion.  After each
        # conversion, call printResult to print the converted string.
        # Note that this code assumes the $a0 register still contains the
        # correct address of the string that was passed to convert, as
        # is required by the procedure calling conventions.

        la    $a0, name01
        li    $a1, 0          # convert upper-case to lower-case
        jal   convert
        jal   printResult

        la    $a0, name02
        li    $a1, 1          # convert lower-case to upper-case
        jal   convert
        jal   printResult

done:   # Epilogue for main -- restore stack & frame pointers and return
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code

printResult:
        # Function Prologue
        subu  $sp, $sp, 24    # allocate stack space -- default of 24
        sw    $fp, 0($sp)     # save the caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        sw    $a0, 8($sp)     # save contents of $a0
        sw    $a1, 12($sp)    # save contents of $a1
        addiu $fp, $sp, 24    # setup printResult's frame pointer

        li    $v0, 4          # print_str
        syscall

        la    $a0, newline
        li    $v0, 4
        syscall

printResultDone:
        # Epilogue for printResult 
        # restore stack & frame pointers & return
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code

# Your code goes below this line

# written by Dominic Martinez

# this will walk through a string - char by char
# and depending on if it fits the properties of 
# being in either the lower or uppercase
# it will convert to which ever one is wanted

convert:
	
     # initiliazing index
     add $t1, $zero, $zero
     
     # checking if we want upper or lowercase
     beq $a1, $zero, lower
	
upper:
     
     # address of name[index]
     add $t7, $a0, $t1
          
     # $t3 = ascii value of name[index]
     lb $t3, ($t7)
     
     #check to see if it is a null
     beq $t3, $zero, end
     
     # check to see if it is lowercase
     # using upper limit of ascii chart
     slti $t6, $t3, 123

     # if it is not uppercase skip
     beq $t6, $zero, skip

     # tweaking the upper limit
     # to be the lower limit
     addi $t5, $zero, 123
     addi $t5, $t5, -27
     
     # check to see if it is any other char
     sgt $t6, $t3, $t5
         
     # if it is another char - skip
     beq $t6, $zero, skip

     # uppercasing    
     addi $t3, $t3, -32

skip:     
     # put lowercase back in string
     sb $t3, ($t7)
     
     # index++
     add $t1, $t1, 1
     
     # go back up to continue 
     b upper
     
lower:     
     
     # address of name[index]
     add $t7, $a0, $t1
          
     # $t3 = ascii value of name[index]
     lb $t3, ($t7)
     
     #check to see if it is a null
     beq $t3, $zero, end
     
     # check to see if it is uppercase
     # using upper limit of ascii chart
     slti $t6, $t3, 91

     # if it is not uppercase skip
     beq $t6, $zero, skip2

     # check to see if it is uppercase
     # using the lower limit of ascii chart
     addi $t5, $zero, 64
          
     # check to see if it is any other char
     slt $t6, $t5, $t3
         
     # if it is another char - skip
     beq $t6, $zero, skip2
     
     # lowercasing    
     addi $t3, $t3, 32
     
skip2:
     # put lowercase back in string
     sb $t3, ($t7)
     
     # index++
     add $t1, $t1, 1
          
     # go back to continue traversing the string
     b lower
     
end: 

     # this returns to main     
     jr $ra    