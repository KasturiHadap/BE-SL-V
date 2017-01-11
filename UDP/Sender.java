/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package send_kasturi1;



import java.net.*;
public class Main{
  public static void main(String[] args) throws Exception {
    DatagramSocket ds = new DatagramSocket();
    String str = "Welcome java";
    InetAddress ip = InetAddress.getByName("172.16.231.166");

    DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 3000);
    ds.send(dp);
    ds.close();
  }
}

