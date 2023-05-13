default:
	javac *.java

run:
	java Main

clean:
	rm -f *.class
	reset
	clear

tar:
	tar -cvz Cell.java Database.java Node.java DatabaseException.java Treap.java -f .tar.gz

unzip:
	tar -zxvf *.tar