package org.alma.distributedforum.server;

import org.alma.distributedforum.client.ICustomerView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISubject extends Remote {

	boolean subscription(ICustomerView view) throws RemoteException;

	boolean unsubscribe (ICustomerView view) throws RemoteException;

	void putMessage(String message) throws RemoteException;

	String getName() throws RemoteException;

}
