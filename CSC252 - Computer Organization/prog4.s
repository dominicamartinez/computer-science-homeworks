
.data
size:    .word   4
courses:
         .word   course_name01
         .asciiz "C SC252 "
         .half   80
         .half   80
         .word   course_name01
         .asciiz "C SC252H"
         .half   15
         .half   4
         .word   course_name02
         .asciiz "C SC443 "
         .half   25
         .half   25
         .word   course_name03
         .asciiz "C SC433 "
         .half   8
         .half   8

course_name01: .asciiz "Computer Organization"
course_name02: .asciiz "Theory of Graphs and Networks"
course_name03: .asciiz "Computer Graphics"

tab:           .asciiz "\t"
newline:       .asciiz "\n"
closedString:  .asciiz "The following courses are full\n"
openString:    .asciiz "The following courses are not full\n"

.text
main:
        # Function prologue -- even main has one
        subu  $sp, $sp, 24    # allocate stack space -- default of 24 here
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        addiu $fp, $sp, 24    # setup main's frame pointer

        # body of main

        la    $a0, courses
        lw    $a1, size
        jal   closed

        la    $a0, courses
        lw    $a1, size
        jal   open

done:   # Epilogue for main -- restore stack & frame pointers and return
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code

# Your code goes below this line

# Dominic Martinez
# this program will go through a certain number of courses
# and detail whether they are open or closed

closed: 

        subu  $sp, $sp, 24    # allocate stack space
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        addiu $fp, $sp, 24    # setup main's frame pointer
	
	# saving the address of courses
	add $t0, $zero, $a0	

loop:

	# do {
		
	# load max size
	lbu $t1, 14($t0)
	
	# load currently enrolled
	lbu $t2, 16($t0)
	
	# if they not equal skip to mark
	bne $t1, $t2, mark
	
	# else print it is closed
	la $a0, closedString
	li $v0, 4
	syscall
	
	# and print it's information
	# with the passed parameter
	add $a0, $zero, $t0
	
	jal printCourse

mark:
	# size--
	addi $a1, $a1, -1
	
	# } while (size > 0)
	beq $a1, $zero, end
	
	# next part of our array
	addi $t0, $t0, 20
	
	# branch to top
	b loop

end:	
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code


printCourse:

        subu  $sp, $sp, 24    # allocate stack space
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        addiu $fp, $sp, 24    # setup main's frame pointer


	# getting the address of all the information
	add $s0, $zero, $a0
	
	# this is only for the course numbers
	addi $s1, $s0, 4
	
	# print it
	add $a0, $s1, $zero
	li $v0, 4
	syscall
		
	# print the tab
	la $a0, tab
	li $v0, 4
	syscall
	
	# get the max size
	lbu $s1, 14($s0)
	add $a0, $zero, $s1
	li $v0, 1
	syscall

	# print the tab
	la $a0, tab
	li $v0, 4
	syscall

	# get the currently enrolled size
	lbu $s1, 16($s0)
	add $a0, $s1, $zero
	li $v0, 1
	syscall
	
	# print the tab
	la $a0, tab
	li $v0, 4
	syscall
	
	# print the course name
	lw $s1, ($s0)
	add $a0, $zero, $s1
	li $v0, 4
	syscall
	
	# print the newline
	la $a0, newline
	li $v0, 4
	syscall

	# epilogue
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code


open: 

        subu  $sp, $sp, 24    # allocate stack space
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        addiu $fp, $sp, 24    # setup main's frame pointer
	
	# saving the address of courses
	add $t0, $zero, $a0	

loopy:

	# do {
		
	# load max size
	lbu $t1, 14($t0)
	
	# load currently enrolled
	lbu $t2, 16($t0)
	
	# if they not equal skip to mark
	beq $t1, $t2, notch
	
	# else print it is closed
	la $a0, openString
	li $v0, 4
	syscall
	
	# and print it's information
	# with the passed parameter
	add $a0, $zero, $t0
	
	jal printCourse

notch:
	# size--
	addi $a1, $a1, -1
	
	# } while (size > 0)
	beq $a1, $zero, ending
	
	# next part of our array
	addi $t0, $t0, 20
	
	# branch to top
	b loopy

ending:	
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code
