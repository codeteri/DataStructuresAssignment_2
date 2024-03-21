JC = javac
JFLAGS = -g -d bin -sourcepath src

# Compile all .java files in src directory
bin/%.class: src/%.java
	$(JC) $(JFLAGS) $<

# Define sources and corresponding class files
SOURCES = $(wildcard src/*.java)
CLASSES = $(SOURCES:src/%.java=bin/%.class)

# Default target to compile all sources
default: $(CLASSES)

# Generate Javadoc
javadoc:
	javadoc -d doc -sourcepath src $(SOURCES)

# Clean compiled class files
clean:
	$(RM) bin/*.class

# Run the GenericsKbAVLApp class
run_avl: bin/GenericsKbAVLApp.class
	java -cp bin GenericsKbAVLApp
