

package client;

import java.io.*;
import java.net.*;
public class Client
{
    public static void main(String[] args)
    {
        try
        {
            Socket s=new Socket("172.16.231.166",6666);
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            dout.writeUTF("Hello Mayuri");
            dout.flush();
            dout.close();
            s.close();
        }
        catch(Exception e){System.out.println(e);}
    }
}