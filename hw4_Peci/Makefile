###################################################
# file:		Makefile for homework 4
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
XXX=4

# Editor (My preferred editor is "gvim")
gvimHome="/c/Program Files (x86)/Vim/vim80"
gvim="$(gvimHome)/gvim.exe"

###################################################
# Variables:
###################################################
# program p (you can set this in command line!)
p=Hello
p=List
p=MatrixBig

# default command line arguments (you can set this at command line)
ss=111
nn=100
mm=3
arg0=$(ss)
arg1=$(nn)
arg2=$(mm)
arg3=0
arg4=0
args=$(arg0) $(arg1) $(arg2) $(arg3) $(arg4)

xlint=-Xlint:unchecked -Xlint:deprecation
xlint=

###################################################
# Targets:  The first target is always the default target!
###################################################
default: c 

c javac compile: 
	-test -d bin || mkdir bin
	javac $(xlint) -d bin src/*.java

r java run: 
	@java -classpath bin ${p} ${args}

cr: compile run

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

t1 test1 MatchAB:
	@echo "=================================================="
	@echo "==>test1 is for MatchAB"
	@echo "=================================================="
	@java -classpath bin MatchAB 111 \
		1 src/ppp.txt src/aaa.txt src/bbb.txt

t2 test2 List:
	@echo "=================================================="
	@echo "==>test2 is for List"
	@echo "=================================================="
	@java -classpath bin List 

t3 test3 fibLog MatrixBig:
	@echo "=================================================="
	@echo "==>test3 is for testing MatrixBig and fibLog"
	@echo "=================================================="
	@java -classpath bin MatrixBig $(arg0) $(arg1) $(arg2)

# NOTE: this test illustrates the recursive call on Makefile!!
t4 test4:
	@echo "=================================================="
	@echo "==>test4 "
	@echo "=================================================="
	@make run p=Hello 

t5 test5:
	@echo "=================================================="
	@echo "==>test5 "
	@echo "=================================================="
	@java -classpath bin CircularlyLinkedList 111 9 

tall testAll: t0 t1 t2 t3 
	@echo "=================================================="
	@echo "==>TESTING t0 to t3"
	@echo "=================================================="

###################################################
# Housekeeping:
###################################################
clean:
	-rm -f -r bin *~ .[^.]* ..?* src/{*~,.[^.]*,..?*} ioFile

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

