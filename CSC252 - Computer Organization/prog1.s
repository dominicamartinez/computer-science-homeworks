.data
size:    .word  11
epsw:    .word   9
         .word -17
         .word  14
         .word   9
         .word -16
         .word  98
         .word -87
         .word -14
         .word -17
         .word  18
         .word  42
oddstr:  .asciiz "The odd sum is "
evenstr: .asciiz "The even sum is "
newline: .asciiz "\n"

# Your code goes below this line

# need this don't know how to use zero constant
.data
zero: .asciiz "0" 

# written by Dominic Martinez

.text
main: 	
	# loading address for size
	la $t0, size
	
	# loading size
	lw $s0, ($t0)
	
	# if size == 0 branch end0
	beq $s0, 0, end0  #
	
	# getting address of array[0]
	la $t1, epsw

loop:   
	# getting data from array 
	lw $s1, ($t1)
	
	# summing even numbers
	add $s2, $s2, $s1
	
	# size decreasing
	sub $s0, $s0, 1
	
	# checking loop invariant
	beq $s0, 0, end
	
	# get next element	
	add $t1, 4
	
        # getting data from array
        lw $s1, ($t1)
        
        # summing odd numbers
        add $s3, $s3, $s1
        
        # size decreasing
        sub $s0, $s0, 1
        
        # checking loop invariant
        beq $s0, 0, end       
        
        #get next element
        add $t1, 4
        
        # return to beginning 
        b  loop  
        
        # loop ends here

	
end0:	
	# gets address for string
	# then loads and prints
	la $a0, oddstr  
	li $v0, 4    
        syscall
        # syscall value for print_string
   
        la $a0, zero   
        li $v0, 4
        syscall
        
        la $a0, newline
        li $v0, 4
        syscall
        
        la $a0, evenstr
        li $v0, 4
        syscall
        
        la $a0, zero   
	li $v0, 4
	syscall

        la $a0, newline
        li $v0, 4
        syscall
              
        li $v0, 10 # exit
        syscall
	
end:
	# loads string
	la $a0, oddstr
	li $v0, 4
	syscall
	
        # Print the even sum
        add $a0, $s3, $zero
        li $v0, 1
        syscall
        
        la $a0, newline
        li $v0, 4
        syscall
        
	la $a0, evenstr
	li $v0, 4
	syscall

        # Print the odd sum
        add $a0, $s2, $zero
        li $v0, 1
        syscall
        
        la $a0, newline
        li $v0, 4
        syscall
        
        li $v0, 10
        syscall