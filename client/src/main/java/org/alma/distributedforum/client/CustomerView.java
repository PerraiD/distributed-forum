package org.alma.distributedforum.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerView extends UnicastRemoteObject implements org.alma.distributedforum.client.ICustomerView {



	protected CustomerView() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void affiche() throws RemoteException {
		// TODO Auto-generated method stub

	}




}