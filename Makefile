OUTPUT = bin

all: sysats-client sysats-server

sysats-server: server/Server.java
	javac -cp ../ -d $(OUTPUT) server/Server.java

sysats-client:
	javac -cp ../ -d $(OUTPUT) client/Main.java

clean:
	rm -rf bin
