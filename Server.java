// A Java program for a Server
import java.net.*;
import java.io.*;

//Calculator program
class Calculator{
String stack[]=new String[50]; 
int top=-1;
void expStack(String exp){
    String s1="";
  top=-1;
for(int i=0;i<exp.length();i++){
   if(exp.charAt(i)>=48&&exp.charAt(i)<=57){
    s1+=exp.charAt(i);
   }
   else
   {
    stack[++top]=s1;
    stack[++top]=""+exp.charAt(i);
    s1="";
   }
}
stack[++top]=s1;

}

int calculate(String exp){
    
    int a;
    expStack(exp);
    String temp[]=new String[50];
    int t=-1;
    for(int i=0;i<top+1;i++)
    {
   
        if(stack[i].equals("*"))
        {
            a=Integer.parseInt(temp[t--])*Integer.parseInt(stack[i+1]);
            
            temp[++t]=""+a;
            i++;

        }
        else
        if(stack[i].equals("/"))
        {
            a=Integer.parseInt(temp[t--])/Integer.parseInt(stack[i+1]);
            
            temp[++t]=""+a;
            i++;
            
        }
        else
            temp[++t]=stack[i];
        
    }
    stack=temp;
    
    int result=Integer.parseInt(stack[0]);
    for(int i=1;i<t;i++)
    {
        
        if(stack[i].equals("+"))
        {
           
            result+=Integer.parseInt(stack[i+1]);
            
                
        }
        else
        if(stack[i].equals("-"))
        {
            result-=Integer.parseInt(stack[i+1]);
            }
    }
    
    return result;


}

}

//calculator end

//Server

public class Server
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       = null;
    private DataOutputStream output  = null;
    // constructor with port
    public Server(int port)
    {
        
        Calculator calc=new Calculator();
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);

            System.out.println("Server started");
 
            System.out.println("Waiting for a client ...");
 
            socket = server.accept();
            System.out.println("Client accepted");
 
            // takes input from the client socket
            in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
            output= new DataOutputStream(socket.getOutputStream());
            String line = "";
            
 
            // reads message from client until "Over" is sent
           
                try
                {
                    line = in.readUTF();
                   
                    output.writeUTF(""+calc.calculate(line));
 
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            
            System.out.println("Closing connection");
 
            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
 
    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}

