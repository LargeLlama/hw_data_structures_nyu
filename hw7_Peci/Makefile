###################################################
# file:		Makefile for homework 7
# 
# This sample Makefile is based on the src + bin folder
# 	organization for your Java programs.  
###################################################
# HOW TO USE THIS MAKEFILE:
#
# 	From any terminal:
# 		> cd $(yourpath_to_hw1)/hw2_Yap	-- go to hw2_Yap folder
# 		> make 				-- this compiles all the Java programs in src.
# 				   All the *.class files will be put
# 				   into a folder called "bin".  If "bin" does
# 				   not exist, it will first create "bin".
# 		> make test		-- this pre-defined test accepts all the defaults 
# 		> make test0	-- this one allows you to override any default
# 		> make testX	-- where X=1,2,3,4,5,6,7
#							are standard tests, with hard coded arguments!
# 							DO NOT CHANGE THEM!
#		> make zip		-- creates hwXXX_YYY.zip for submission
###################################################
#
#  -- Professor Chee Yap
#     Data Structures Course (CS 102)
#
###################################################
# System Setup:  Customize for your own platform (Windows, MacOS, etc)
###################################################
# Who are you?
YYY=Peci

# Which homework?
XXX=7

###################################################
# Variables:
###################################################
# program p (you can set this in command line!)
p=Hello
p=MyStack
p=MyQueue
p=BST
p=Split
p=AVL
p=MyBST

# default command line arguments (you can set this at command line)
ss=111
nn=9
mm=3
arg0=$(ss)
arg1=$(nn)
arg2=$(mm)
arg3=0
arg4=0
args=$(arg0) $(arg1) $(arg2) $(arg3) $(arg4)

xlint=-Xlint:unchecked -Xlint:deprecation
xlint=
src=src/
#src=

###################################################
# Targets:  The first target is always the default target!
###################################################
default: c 

c1 compile1: 
	-test -d bin || mkdir bin
	javac $(xlint) -d bin $(src)$(p).java

c ca javac compile compileAll: 
	-test -d bin || mkdir bin
	javac $(xlint) -d bin $(src)*.java

r java run: 
	@java -classpath bin ${p} ${args}

r0 java0 run0: 
	@echo "Run with all the defaults"
	@java -classpath bin ${p}

cr: compile run0

# Do you know why we have "@" in front of java? Remove it and see.
h Hello:
	@java -classpath bin Hello ${args}

t test:
	@echo "=================================================="
	@echo "==>test accepts all the default arguments of the Java code:"
	@echo "=================================================="
	@java -classpath bin $(p) 

t0 test0:
	@echo "=================================================="
	@echo "==>test0 allows you to modify any argument in calling 'make'"
	@echo "=================================================="
	@java -classpath bin $(p) ${args}

###################################################
# Special Targets:
###################################################
t1 test1 Split :
	@echo "=================================================="
	@echo "==>test1 is for Split (fixed args)"
	@java -classpath bin Split 111 8

t2 test2 MyBST:
	@echo "=================================================="
	@echo "==>test2 is for MyBST (fixed args)"
	@java -classpath bin MyBST 111 3 0

t2a test2a BST:
	@echo "=================================================="
	@echo "==>test2a is for BST (default args)"
	@java -classpath bin BST 

t3 test3 AVL :
	@echo "=================================================="
	@echo "==>test3 is for AVL (fixed args)"
	@java -classpath bin AVL 111 

t3a test3a AVLa :
	@echo "=================================================="
	@echo "==>test3 is for AVL (your args)"
	@java -classpath bin AVL $(args)

###################################################
# Housekeeping:
###################################################
clean:
	-rm -f -r bin *~ .[^.]* ..?* $(src){bin,*~,.[^.]*,..?*} ioFile

## This is VERY useful for submission of homework!
##		BUT make sure that your set YYY to your own last name.
##		You can submit the zip file found in the parent folder:
zip:	clean
	cd ..; zip hw$(XXX)_$(YYY).zip -r hw$(XXX)_$(YYY)

## This is for us to produce our solution:
zipsol:	clean
	cd ..; zip hw$(XXX)_Sol.zip -r hw$(XXX)_Sol
###################################################
# End
###################################################

