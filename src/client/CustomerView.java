package client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerView extends UnicastRemoteObject implements ICustomerView,Serializable {



	protected CustomerView() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	@Override
	public void show() throws RemoteException {
		
		
	}

	
	

}
