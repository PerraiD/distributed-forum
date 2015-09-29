package org.alma.distributedforum.client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerView extends UnicastRemoteObject implements ICustomerView,Serializable {

	protected CustomerView() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
<<<<<<< HEAD:src/client/CustomerView.java
	public void show() throws RemoteException {
		
		
=======
	public void affiche() throws RemoteException {
		// TODO Auto-generated method stub
>>>>>>> f08f183bda032323e9b1b4b8ec13c762283a7f93:client/src/main/java/org/alma/distributedforum/client/CustomerView.java
	}
}
