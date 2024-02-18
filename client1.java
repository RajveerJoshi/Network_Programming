import java.io.*;
import java.net.*;
class Client
{
public static void main(String []args)
{

//data is taken as command line argument 
try
{
int rollNumber=Integer.parseInt(args[0]);
String name=args[1];
String gender=args[2];
String request=rollNumber+","+name+","+gender+"#";// act as a terminator
Socket socket=new Socket("localhost",5500);
// Socket is initialized and attempt is made for Connecting to the server

// Declearing other Properties and Streams
InputStream is;
InputStreamReader isr;
OutputStream os;
OutputStreamWriter osw;
String response;
StringBuffer sb;
int x;

// retrieving output Stream and its witer, for sending request or acknowledgement
os=socket.getOutputStream();
osw=new OutputStreamWriter(os);
osw.write(request);
osw.flush();
//request sent

// retrieving input Stream and its reader, for receiving response or acknowledgement
is=socket.getInputStream();
isr=new InputStreamReader(is);
sb=new StringBuffer();
while(true)
{
x=isr.read();
if(x==-1||x=='#') break; // read till the terminator
sb.append((char)x);
}
response=sb.toString();
System.out.println("Response is:"+response);
socket.close(); // closing the Connection
}catch(Exception e)
{
//Raised in case, Connection is refused or some other technical issue
System.out.println(e);
}
}
}
