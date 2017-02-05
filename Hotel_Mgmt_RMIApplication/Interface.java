import java.rmi.*;

public interface Interface extends Remote //for stub
{
	
	public int login(String uname,String password) throws RemoteException;
	public int placeOrder(String lname, int mid, int qty) throws RemoteException;
	public int cancelOrder(int no) throws RemoteException;
	public int totalOrders() throws RemoteException;
}