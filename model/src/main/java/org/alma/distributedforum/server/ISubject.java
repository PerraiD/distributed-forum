package org.alma.distributedforum.server;

import org.alma.distributedforum.client.ICustomerView;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Subject of forum server
 *
 * @author dralagen
 */
public interface ISubject extends Remote {

	/**
	 * Subscribe a client view to one subject
	 *
	 * @param view client view
	 * @return true if the subscription is successful
	 * @throws RemoteException
	 */
	boolean subscribe(ICustomerView view) throws RemoteException;

	/**
	 * Unsubscribe a client view to one subject
	 *
	 * @param view client view
	 * @return true if the unsubscription is successful
	 * @throws RemoteException
	 */
	boolean unsubscribe(ICustomerView view) throws RemoteException;

	/**
	 * Send a message to all listener of the subject
	 *
	 * @param message the message
	 * @throws RemoteException
	 */
	void sendMessage(String message) throws RemoteException;

	/**
	 * Get name of subject
	 * @return name of subject
	 * @throws RemoteException
	 */
	String getName() throws RemoteException;
	
	/**
	 * Get list of the history of subject message
	 * @throws RemoteException
	 * @return list of message
	 */
	List<String> getHistory() throws RemoteException;

}
