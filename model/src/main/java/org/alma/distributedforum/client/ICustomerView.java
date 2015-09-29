package org.alma.distributedforum.client;

import java.rmi.Remote;

import java.rmi.RemoteException;

public interface ICustomerView extends Remote {

	void show() throws RemoteException;
}
