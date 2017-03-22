/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication3;

//Simple TCP Client

import java.net.*;
import java.io.*;

public class TCPClient
{
	public static void main(String[] args) throws Exception
	{
		String str = null;
 BufferedReader kyrd = new BufferedReader(new InputStreamReader(System.in));

		InetAddress inet = InetAddress.getLocalHost();
		Socket cSock = new Socket(inet,4440);
		DataInputStream in  = new DataInputStream(cSock.getInputStream());
		DataOutputStream out = new DataOutputStream(cSock.getOutputStream());
		out.writeUTF("Hello From Client...");
		System.out.println("Server : " + in.readUTF().toString());


		while (true) {
            str = kyrd.readLine();
            out.writeBytes(str);
            out.write(13);
            out.write(10);
            out.flush();
            String s;
            s = in.readLine();
            System.out.println("From server :" + s);
            if (s.equals("exit")) {
                break;
								  }
					}
        in.close();
        out.close();
		cSock.close();
	}
}

