package org.alma.distributedforum.client;

import java.rmi.Remote;

import java.rmi.RemoteException;

public interface ICustomerView extends Remote {

	/**
	 * Listener of all new messages of the subject
	 *
	 * @param message new message
	 * @throws RemoteException
	 */
	void show(String message) throws RemoteException;
}
