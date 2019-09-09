args=
p=TestSquare

default:
	@-test -d bin/ || mkdir bin/
	@javac -d bin/ src/*.java

run:
	@java -classpath bin ${p} ${args}

clean:
	@rm -rf bin/
