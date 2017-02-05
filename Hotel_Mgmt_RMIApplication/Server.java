import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Server
{
	public static void main(String args[])
	{
		try
		{
			Implemention i=new Implemention(); //remote object
			int port=4444;
			String hostname="127.0.0.3";
			String bindloc="//"+hostname+":"+port+"/Hotel";
			LocateRegistry.createRegistry(port);
			System.out.println("JavaRMI registry is created successfully...");
			Naming.bind(bindloc, i); //bind with remote object
			System.out.println("Server is ready at:"+bindloc);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}


}
