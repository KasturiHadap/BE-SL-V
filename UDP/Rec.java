/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rec_kasturi;

import java.io.*;
import java.net.*;
public class Main {
public static void main(String[] args) {
try{
Socket s=new Socket("localhost",3000);
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
dout.writeUTF("Hello Server");
dout.flush();
dout.close();
s.close();
}catch(Exception e){System.out.println(e);}
}
}
