Shttpd (simple-httpd) program
====

Create a program similar to the SimpleHTTPServer module in Python.<br>
simple-httpd works as follows:
----

- The program is executed with a specified port number.
- The program listens on the specified port number.
- The program serves as a web server with the directory it was executed from as the document-root.
- When a GET / request is made, the program responds with an HTML list of the current directory's contents.
- When a GET /file-path request is made:
  - If the file exists, the program responds with a 200 OK status and the file contents.
  - If the file does not exist, the program responds with a 404 Not Found status.
  - The program must include appropriate response headers (Content-Type, Content-Length).
- If a directory higher than the document-root is requested, the program responds with a 403 Forbidden status.
- If a file without read permissions is requested, the program responds with a 403 Forbidden status.
- The program implements multipart/form-data file uploads:
  - Uploaded files are saved directly in the directory where the program was executed.
  - If the program does not have write permissions, it responds with a 403 Forbidden status.
  - If a file with the same name already exists, it responds with a 409 Conflict status.
- POST requests other than multipart/form-data file uploads are responded with a 405 Method Not Allowed status.
- The program implements the DELETE method:
  - If the file specified in the URL can be deleted, the program deletes it and responds with a 204 No Content status.
  - If the file specified in the URL does not exist, the program responds with a 204 No Content status.
  - If the file specified in the URL cannot be deleted, the program responds with a 403 Forbidden status.
- The program outputs the received requests on the screen (access log):
  - Time, request method, path, response code, response size, and response time.

## Usage:
<img width="187" alt="image" src="https://user-images.githubusercontent.com/103839217/229298536-ff6f70f1-7051-4cd7-8f70-d4dc84057854.png">
