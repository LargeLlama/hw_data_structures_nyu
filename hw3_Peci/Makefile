###################################################
# file:		Makefile for homework 3
# 
# This sample Makefile is based on the src + bin folder
# 	organization for your Java programs.  
#
# Assume you have a folder called hw2_YYY (where YYY is your last
#	name, with initial capital letter).  We use YYY=Yap to illustrate.
#	The folder should contain
# 		(1) this Makefile
# 		(2) A folder called src where all java sources are stored.
#  			For homework 2, we have these files in src:
# 					Bday.java
#					Paradox.java
###################################################
# HOW TO USE THIS MAKEFILE:
#
# 	From any terminal:
#
# 		> cd $(yourpath_to_hw1)/hw2_Yap	-- go to hw2_Yap folder
#
# 		> make 				-- this compiles all the Java programs in src.
# 				   All the *.class files will be put
# 				   into a folder called "bin".  If "bin" does
# 				   not exist, it will first create "bin".
# 		> make test		-- this pre-defined test accepts all the defaults 
# 		> make test0	-- this one allows you to override any default
#
# 		> make testX	-- where X=1,2,3,4,5,6,7
#							are standard tests, with hard coded arguments!
# 							DO NOT CHANGE THEM!
#
#		> make zip		-- creates hwXXX_YYY.zip for submission
#
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
XXX=3

###################################################
# Variables:
###################################################
# program p (you can set this in command line!)
p=MatchAB

# default command line arguments (you can set this at command line)
arg0=
arg1=
arg2=
arg3=
args=$(arg0) $(arg1) $(arg2) $(arg3)

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
# We have an "@" because it "silents" the command in the sense that it runs it without showing it or irrelevant feedback
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

t1 test1:
	@echo "=================================================="
	@echo "==>test1 is like test, with seed ss=111"
	@echo "=================================================="
	@java -classpath bin MatchAB 111 \
		1 src/pp.txt src/aa.txt src/bb.txt

t2 test2:
	@echo "=================================================="
	@echo "==>test2 is like test1, but input files can change"
	@echo "=================================================="
	@java -classpath bin MatchAB 0 \
		1 $(_pat) $(_a) $(_b)

t3 test3:
	@echo "=================================================="
	@echo "==>test3 is like test1, but interactive mode"
	@echo "=================================================="
	@java -classpath bin MatchAB 0 \
		0 $(_pat) $(_a) $(_b)


# NOTE: we now call Makefile recursively 
t4 test4:
	@echo "=================================================="
	@echo "==>test4 should be slightly over 50% successful as nn=24 "
	@echo "=================================================="
	@make run p=MatchAB 

tall testAll: t0 t1 t2 t3 t4 
	@echo "=================================================="
	@echo "==>TESTING t0 to t4"
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

