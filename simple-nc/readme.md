Snc (Simple-nc) Program
====
## This program, called simple-nc, functions similarly to the nc (netcat) program.

### In client mode:
- The program will establish a TCP connection to the server specified in the input arguments.
- User input (STDIN) will be sent to the server.
- Data received from the server will be displayed on the standard output (STDOUT).
- The program can be terminated by pressing ctrl-c.

### In server mode:
- The program will accept a listen port as input arguments.
- It will run a TCP server on the specified port and wait for a connection.
- When a client connects and sends data, the data will be displayed on the standard output.
- User input (STDIN) will be sent to the client.
- The program can be terminated by pressing ctrl-c.

### Usage
Usage: snc [option] [hostname] [port] <br>
Options:<br>
-l <port> Run in server mode and listen on the specified port.<br>
  
### Example Usage 1:
  <img width="152" alt="image" src="https://user-images.githubusercontent.com/103839217/229297514-cfca1295-c4d1-4a98-99f1-4cec943f50aa.png">
 
  - The program will run in server mode.
  - It will accept user input and send it to the client.
  - Data received from the client will be displayed on the standard output.
  - The program can be terminated by pressing ctrl-c.
  - Only one connection will be processed.
  
### Example Usage 2:
  <img width="201" alt="image" src="https://user-images.githubusercontent.com/103839217/229297568-0bb62781-cdf9-4c1d-b88d-fa617aebb849.png">

  - The program will run in client mode.
  - It will accept user input and send it to the server.
  - Data received from the server will be displayed on the standard output.
  - Both fqdn and IP can be used as the hostname.
  - The program can be terminated by pressing ctrl-c.
  
