import java.io.*;
import java.net.*;
class RequestProcessor extends Thread // for multiThreading server
{
private Socket socket;
RequestProcessor(Socket socket)
{
this.socket=socket;
start(); //will load the run method
}
public void run()
{
try
{

//Declearing properties ans streams
InputStream is;
InputStreamReader isr;
OutputStream os;
OutputStreamWriter osw;
StringBuffer sb;
String request;
String response;

int x;
int c1,c2;
String pc1,pc2,pc3;
int rollNumber;
String name;
String gender;

// getting input stream and its reader, for reading request or acknowledgement
is=socket.getInputStream();
isr=new InputStreamReader(is);
sb=new StringBuffer();
while(true)
{
x=isr.read();
if(x==-1||x=='#') break; //reads until terminator
sb.append((char)x);
}
request=sb.toString();
System.out.println("Request Arrived:"+request);

//parsing and extracting Request data
c1=request.indexOf(",");
c2=request.indexOf(",",c1+1);
pc1=request.substring(0,c1);
pc2=request.substring(c1+1,c2);
pc3=request.substring(c2+1);
rollNumber=Integer.parseInt(pc1);
name=pc2;
gender=pc3;
System.out.printf("Roll number %d,Name %s,Gender %s\n",rollNumber,name,gender);

// handle data

// sending response
response="data Saved #";

//get output stream and its writer, for sending response or acknowledgement
os=socket.getOutputStream();
osw=new OutputStreamWriter(os);
osw.write(response);
osw.flush();
//response sent
System.out.println("Response sent");
socket.close();//terminating Connection
}catch(Exception e)
{
System.out.println(e);
}
}
}
class Server
{
private ServerSocket serverSocket;
Server()
{
try
{

//Initiating ServerSocket with TCP port
serverSocket=new ServerSocket(5500);
startListening();
}catch(Exception e)
{
System.out.println(e);
}
}
private void startListening()
{
try
{
Socket socket;
RequestProcessor requestProcessor;
while(true)
{
System.out.println("Server is ready to accept request on port 5500:");
socket=serverSocket.accept();
// server is in listening mode
requestProcessor=new RequestProcessor(socket);
}
}catch(Exception e)
{
System.out.println(e);
}
}
public static void main(String []args)
{
Server server=new Server();
}
}
