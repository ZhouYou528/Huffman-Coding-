JFLAGS = -g
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
Node.java \
MinHeap.java \
hNode.java \
packEncodedChars.java \
HuffmanTree.java \
encoder.java \
decoder.java 

default: classes
classes: $(CLASSES:.java=.class)
clean:
	$(RM) *.class
