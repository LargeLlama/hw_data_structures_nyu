###################################################
# file:		Makefile for homework 2
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
YYY=Yap

# Which homework?
XXX=2

# Java  (I am using Windows with Cygwin)
javaHome="/cygdrive/c/Program Files/Java/jdk-11.0.2/bin"
javac=$(javaHome)/javac.exe
java=$(javaHome)/java.exe

# Editor (My preferred editor is "gvim")
gvim="/c/Program Files (x86)/Vim/vim80/gvim.exe"

###################################################
# Variables:
###################################################
# program p (you can set this in command line!)
p=FileIO
p=HelloClass
p=Hello
p=Paradox

# default command line arguments (you can set this at command line)
arg0=
arg1=
arg2=
arg3=
args=$(arg0) $(arg1) $(arg2) $(arg3)

###################################################
# Targets:  The first target is always the default target!
###################################################
default: c 

c javac compile: 
	-test -d bin || mkdir bin
	$(javac) -d bin src/*.java

r java run: 
	@$(java) -classpath bin ${p} ${args}

cr: compile run

# Do you know why we have "@" in front of $(java)?  Try removing it.
h Hello:
	@$(java) -classpath bin Hello ${args}

t test:
	@echo "=================================================="
	@echo "==>test accepts all the default arguments of the Java code:"
	@echo "=================================================="
	@$(java) -classpath bin Paradox 

t0 test0:
	@echo "=================================================="
	@echo "==>test0 allows you to modify any argument in calling 'make'"
	@echo "=================================================="
	@$(java) -classpath bin Paradox ${args}

t1 test1:
	@echo "=================================================="
	@echo "==>test1 is like test, with seed ss=111: success in mode 0"
	@echo "=================================================="
	@$(java) -classpath bin Paradox 111 24 0 100

t2 test2:
	@echo "=================================================="
	@echo "==>test2 is like test1, but seed is ss=11: failure in mode 0"
	@echo "=================================================="
	@$(java) -classpath bin Paradox 11 24 0 100

t3 test3:
	@echo "=================================================="
	@echo "==>test3 is completely unpredictable since ss=0: "
	@echo "=================================================="
	@$(java) -classpath bin Paradox 0 24 0

# NOTE: we now call Makefile recursively (just for variety:)
t4 test4:
	@echo "=================================================="
	@echo "==>test4 should be slightly over 50% successful as nn=24 "
	@echo "=================================================="
	@make r p=Paradox arg0=111 arg1=24 arg2=1 arg3=100

t5 test5:
	@echo "=================================================="
	@echo "==>test5 should be slightly over 50% successful as nn=24 "
	@echo "=================================================="
	@make r p=Paradox arg0=111 arg1=24 arg2=1 arg3=1000 

t6 test6:
	@echo "=================================================="
	@echo "==>test6 should be highly successful as nn=40 "
	@echo "=================================================="
	@make r p=Paradox arg0=111 arg1=40 arg2=1 arg3=1000

t7 test7:
	@echo "=================================================="
	@echo "==>test7 should be quite unsuccessful as nn=10"
	@echo "=================================================="
	@make r p=Paradox arg0=111 arg1=10 arg2=1 arg3=1000

tall testAll: t0 t1 t2 t3 t4 t5 t6 t7
	@echo "=================================================="
	@echo "==>TESTING t0 to t7"
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

