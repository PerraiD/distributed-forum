package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICustomerView extends Remote{
	
	
	public void affiche() throws RemoteException; 
}
