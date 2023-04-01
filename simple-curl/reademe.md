Scurl (simple-curl) Program
=====

## This is a program called simple-curl that works similarly to the curl program. The scurl program works as follows:

- It receives a URL as an input argument and sends a request, then displays the response on the screen.
- Other methods (HEAD, POST, PUT, DELETE) can be requested using options in addition to GET.
- When using methods like POST and PUT, you can specify the data to be sent.
- By default, it does not display the request and response headers, but you can choose to display them with the appropriate option.
- Only the response with ContentType of "text/*" and "application/json" will be displayed.
- If Content-Type is not specified with H for POST or PUT, application/x-www-form-urlencoded is used as the default type.

## Usage
Usage: scurl [options] url <br>
Options:<br>
-v                 verbose, displays request and response headers<br>
-H <line>          sends arbitrary header to server<br>
-d <data>          sends data to POST, PUT requests<br>
-X <command>       specifies the method to use, default is GET<br>
-L                 follows redirects if the response status code is 30x<br>
-F <name=content>  constructs and sends a multipart/form-data, where the content can include @filename.<br>
  
## Example Usage 1:
  <img width="265" alt="image" src="https://user-images.githubusercontent.com/103839217/229296158-130a3299-a484-4241-b247-92f51c92d5b3.png">
  
## Example Usage 2:
  <img width="332" alt="image" src="https://user-images.githubusercontent.com/103839217/229296182-71a54ca0-7e8e-4441-ac6a-5f8fb2459038.png">

  - Send a GET request to http://httpbin.org/get with the method name explicitly specified.
- Enter a well-known port and send a GET request along with the header.
- Separate headers and bodies with an empty line.
  
## Example Usage 3:
  <img width="302" alt="image" src="https://user-images.githubusercontent.com/103839217/229296222-53b050f0-f7ac-448f-9065-543a5891e04a.png">

  - The request and response headers will be displayed together.
  
## Example Usage 4:
  <img width="499" alt="image" src="https://user-images.githubusercontent.com/103839217/229296294-9c77c0b5-29d6-4dfb-a96f-3ff808bafb1e.png">

  - The request header will include an additional X-Custom-Header: NA field.
  
## Example Usage 5:
  <img width="689" alt="image" src="https://user-images.githubusercontent.com/103839217/229296313-46e50b4c-dc04-466c-91ed-439ff16fa7df.png">
 
  - The POST body will be specified using the -d option.
  - The data following -d will be sent as the entity body.
  
## Example Usage 6:
<img width="348" alt="image" src="https://user-images.githubusercontent.com/103839217/229296576-6a2ade34-ea8f-4bb3-9635-696b6537f706.png">

  - If a 302 response is received, the program will follow the Location specified in the response.
    - If a 301, 302, 307, or 308 response is received after following the Location, the program will follow it again.
    - This process will continue up to a maximum of five times.
    - If the program encounters a sixth redirection message, it will output an error message.
  
## Example Usage 7:
  <img width="477" alt="image" src="https://user-images.githubusercontent.com/103839217/229296596-07b66451-19f1-49fb-bb6f-da32945d1bb4.png">
  
  - In this example, a file specified in file_path will be sent as multipart/form-data.
