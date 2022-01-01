
.data
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
         .word   course_name04
         .asciiz "C SC433 "
         .half   8
         .half   8
         .word   course_name03
         .asciiz "C SC452 "
         .half   95
         .half   25
         .word   0
         .asciiz "NullItem"
         .half   0
         .half   0

course_name01: .asciiz "Computer Organization"
course_name02: .asciiz "Theory of Graphs and Networks"
course_name03: .asciiz "Principles of Operating Systems"
course_name04: .asciiz "Computer Graphics"

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

        la    $a0, closedString
        li    $v0, 4
        syscall

        la    $a0, courses
        jal   closed

        la    $a0, openString
        li    $v0, 4
        syscall

        la    $a0, courses
        jal   open

done:   # Epilogue for main -- restore stack & frame pointers and return
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code

printCourse:
        # Function prologue
        subu  $sp, $sp, 24    # allocate stack space -- default of 24 here
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        sw    $a0, 8($sp)     # save contents of $a0
        addiu $fp, $sp, 24    # setup printCourses's frame pointer

        add   $t0, $a0, $zero # $t0 = addr of this course


        addi  $a0, $t0, 4     # print course number
        li    $v0, 4
        syscall

        la    $a0, tab
        li    $v0, 4
        syscall

        lh    $a0, 14($t0)    # print max size
        li    $v0, 1
        syscall

        la    $a0, tab
        li    $v0, 4
        syscall

        lh    $a0, 16($t0)    # print current size
        li    $v0, 1
        syscall

        la    $a0, tab
        li    $v0, 4
        syscall

        lw    $a0, 0($t0)     # $a0 = addr of name of course
        li    $v0, 4
        syscall

        la    $a0, newline
        li    $v0, 4
        syscall 

        # Epilogue for printCourse -- restore stack & frame pointers & return
        lw    $a0, 8($sp)     # restore contents of $a0
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code

# Your code goes below this line
# Dominic Martinez
# this program recurses over all the course information
# and will print out in reverse order which are full
# and which are closed

closed:
        subu  $sp, $sp, 24    # allocate stack space -- default of 24 here
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        sw    $a0, 8($sp)     # save contents of $a0
        sw    $s0, 12($sp)    # save contents of $s0
        addiu $fp, $sp, 24    # setup printCourses's frame pointer
        
	# get the address of the array
	add $s0, $a0, $zero 	
	
base:	
	# check to see if the record contains EOA
	lw $s1, ($s0)	
	
	# if it is the base case, return
	beq $s1, $zero, mark
	
iter:   
	
	# increment the array index
	addi $a0, $a0, 20
	
	# recurses one more step
	jal closed

tail:	
	# load max size
	lbu $s1, 14($s0)
	
	# load currently enrolled
	lbu $s2, 16($s0)
	
	# if they not equal skip to mark
	bne $s1, $s2, mark
	
	# put it back to the original before call
	addi $a0, $a0, -20
	
	# print the courses
	jal printCourse

mark:	
        # Epilogue - restore stack & frame pointers & return
        lw    $s0, 12($sp)    # restore contents of $s0
        lw    $a0, 8($sp)     # restore contents of $a0
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code


open:
        subu  $sp, $sp, 24    # allocate stack space -- default of 24 here
        sw    $fp, 0($sp)     # save caller's frame pointer
        sw    $ra, 4($sp)     # save return address
        sw    $a0, 8($sp)     # save contents of $a0
        sw    $s0, 12($sp)    # save contents of $s0
        addiu $fp, $sp, 24    # setup printCourses's frame pointer
        
	# get the address of the array
	add $s0, $a0, $zero 	
	
basey:	
	# check to see if the record contains EOA
	lw $s1, ($s0)	
	
	# if it is the base case, return
	beq $s1, $zero, mark
	
itery:   
	
	# increment the array index
	addi $a0, $a0, 20
	
	# recurses one more step
	jal open

tails:	
	# load max size
	lbu $s1, 14($s0)
	
	# load currently enrolled
	lbu $s2, 16($s0)
	
	# if they not equal skip to mark
	beq $s1, $s2, marks
	
	# put it back to the original before call
	addi $a0, $a0, -20
	
	# print the courses
	jal printCourse

marks:	
        # Epilogue - restore stack & frame pointers & return
        lw    $s0, 12($sp)    # restore contents of $s0
        lw    $a0, 8($sp)     # restore contents of $a0
        lw    $ra, 4($sp)     # get return address from stack
        lw    $fp, 0($sp)     # restore the caller's frame pointer
        addiu $sp, $sp, 24    # restore the caller's stack pointer
        jr    $ra             # return to caller's code

