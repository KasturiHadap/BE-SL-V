import java.util.ArrayList;
import static java.util.Collections.list;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Implemention extends UnicastRemoteObject implements Interface
{
	public int n=0;
	ArrayList<Integer> menu_id = new ArrayList<Integer>(){{add(0); add(1); add(2); add(3); add(4); add(5);}};
	ArrayList<String> menu_name = new ArrayList<String>(){{add("Pav Bhaji"); add("Misal Pav"); add("Idli Sambar"); add("Masala Dosa"); add("Dahi Vada");}};
	ArrayList<Integer> menu_price = new ArrayList<Integer>(){{add(40); add(50); add(35); add(25); add(55);}};
	ArrayList<String> password = new ArrayList<String>(){{ add("user1"); add("user2"); add("user3");}};
	ArrayList<String> username = new ArrayList<String>(){{ add("user1"); add("user2"); add("user3");}};
	
	public Implemention() throws RemoteException{}
 
	public int login(String uname,String pass) throws RemoteException
	{
		int flag=0;
		for(int i=0;i<3;i++)
        {
            if(username.get(i).equals(uname)  || password.get(i).equals(pass))
            {
				flag=1;
                break;
            }
            else
				flag=0;
        }
		return flag;
	}
	
	public int placeOrder(String lname, int mid, int qty) throws RemoteException
	{
			n=n+1;
			int pr = menu_price.get(mid);
			int total= qty*pr;
			System.out.println("User "+lname+" has placed "+qty+" order of "+mid+" "+menu_name.get(mid)+".");
			System.out.println("Total price = "+qty+" * "+pr+"  ="+total);
			return total;
			
	}

	public int cancelOrder(int no) throws RemoteException
	{
		System.out.println(no+" of orders are to be canceled.");
		n=n-no;
		System.out.println("Total no. of orders are : "+no);
		return n;
	}

	public int totalOrders() throws RemoteException
	{
		return n;
	}
}
