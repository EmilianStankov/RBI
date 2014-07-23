RBIProtocol
===========

### What is RBI?
RBI stands for Remote Bash Invocation - the ability to invoke bash commands from a client to a server. (something like ssh, but simplified)

### How to run?
The server-side runs the `server.jar` using `java -jar server.jar`.  
Then the client-side runs `client.jar` using `java -jar client.jar`.  
Now the client can send __Bash commands__ to the server by typing them into the terminal. 