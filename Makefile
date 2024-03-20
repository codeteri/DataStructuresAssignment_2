JC = javac
JFLAGS = -g -d bin -sourcepath src

bin/%.class: src/%.java
		$(JC) $(JFLAGS) $<

SOURCES = GenericsKbAVLApp.java Statement.java

CLASSES = $(SOURCES:%.java=bin/%.class)

default: $(CLASSES)

compile_avl:
	javac src/Statement.java
	javac src/GenericsKbAVLApp.java
	javac src/AVLTree.java
	javac src/BinaryTreeNode.java
	javac src/BinaryTree.java
	javac src/BTQueue.java
	javac src/BTQueueNode.java


javadoc:
	javadoc -d doc -sourcepath src/*.java


clean:
	$(RM) bin/*.class

run_array: bin/GenericsKbArrayApp.class
	java -cp bin GenericsKbArrayApp

