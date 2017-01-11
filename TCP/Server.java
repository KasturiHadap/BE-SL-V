

package server;

import java.io.*;
import java.net.*;
public class Server
{
    public static void main(String[] args)
    {
        try
        {
        ServerSocket ss=new ServerSocket(6666);
        System.out.println("Connection Established");
        Socket s=ss.accept();
        System.out.println("Connection Accepted");
        DataInputStream dis=new DataInputStream(s.getInputStream());
        String  str=(String)dis.readUTF();
        System.out.println("message= "+str);
        ss.close();
        }
        catch(Exception e){System.out.println(e);}
    }
}
