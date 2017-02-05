import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.util.*;
class Client
{

	public class MenuList
		{
			public int no;
			public String name;
			public int price;
			MenuList(int no,String name,int price)
			{
				this.no=no;
				this.name=name;
				this.price=price;
			}
			public void display_menucard() throws RemoteException
			{
				MenuList[] ord = new MenuList[5];
				ord[0]=new MenuList(1,"Pav Bhaji",40);
				ord[1]=new MenuList(2,"Misal Pav",50);
				ord[2]=new MenuList(3,"Idli Sambar",35);
				ord[3]=new MenuList(4,"Masala Dosa",25);
				ord[4]=new MenuList(5,"Dahi Vada",55);
				System.out.println("*******************************");
				System.out.println("MenuId	   Menu		Price");
				System.out.println("-------------------------------");
				for(int i=0;i<5;i++)
                {
					System.out.println(ord[i].no+"	"+ord[i].name+"	"+ord[i].price);
                }
				System.out.println("*******************************");

			}
		}

	public static void main(String[] args) 
	{
		
		try
		{
			Scanner sc= new Scanner(System.in);
			int port=4444;
			int total,flag=0;
			String hostname="127.0.0.3";
			String bindloc="//"+hostname+":"+port+"/Hotel";
			Interface si=(Interface)Naming.lookup(bindloc);
			
			while(true)
			{
					System.out.println("\n\n                    HOTEL MANAGMENT SYSTEM ");
					System.out.println("\n\n=============  MENU  ================");
					System.out.println("1.LogIn\n2.Todays Menu\n3.Place Order\n4.Cancel Order\n5.Total no. of orders placed\n6.Exit");
					System.out.println("=====================================");
					System.out.println("[Note : for option 3,4 and 5 you must be logged in]");
					System.out.println("\nEnter your choice :");
					int ch = sc.nextInt();
					switch(ch)
					{
						case 1:
							System.out.println("Enter Username");
							String username=sc.nextLine();	
							System.out.println("Enter Password :");
							String password=sc.nextLine();
							flag=si.login(username,password);
							if(flag==1)
							{
								System.out.println("Logged In Successfully.......");
							}
							else
							{
								System.out.println("Log In Failed.......");
							}
							break;
						case 2:
							Client c = new Client();
							Client.MenuList co = c.new MenuList(0,null,0);
							co.display_menucard();
							break;
						case 3:
							if(flag==1)
							{
								System.out.println("Enter your lastname :");
								String lname=sc.nextLine();
								System.out.println("Enter MenuID :");
								int mid= sc.nextInt();
								System.out.println("Enter Quantity :");
								int qty= sc.nextInt();
								System.out.println(lname+"	"+mid+"	"+qty);
								total=si.placeOrder(lname,mid,qty);
								System.out.println("You have placed order succesfully......");
								System.out.println(" --------------------");
								System.out.println("| Total Price = "+total+" |");
								System.out.println(" --------------------");
							}
							else
								System.out.println("Login First");
							break;
						case 4:
							if(flag==1)
							{
								System.out.println("How many orders you want to cancel? :");
								int r=sc.nextInt();
								total=si.totalOrders();
								if(r<=total)
								{
										total=si.cancelOrder(r);
										System.out.println("Orders are canceled succesfully.......");
										System.out.println("Total orders are : "+total);
								}
								else
									System.out.println("Checkout the total orders...");
							}
							else 
								System.out.println("Login First");
							break;
						case 5:
							if(flag==1)
							{
								int n=si.totalOrders();
								System.out.println("Total no. of orders placed are : "+n);
							}
							else
								System.out.println("Login First");
								break;
						case 6:
							return;
					}	
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
