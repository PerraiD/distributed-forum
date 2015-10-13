package org.alma.distributedforum.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.alma.distributedforum.client.ICustomerForum;
import org.alma.distributedforum.server.exception.SubjectAlreadyExist;
import org.alma.distributedforum.server.exception.SubjectNotFound;
import org.alma.distributedforum.server.exception.SubscribeListeningException;

/**
 * Interface of a distributed forum server
 *
 * @author dralagen
 */
public interface IForumServer extends Remote {

	/**
	 * Server port for network discover
	 */
	int SERVER_PORT = 10000;

	/**
	 * Search on forum server the subject on name theme
	 *
	 * @param name
	 *            name of subject
	 * @return the subject
	 * @throws SubjectNotFound
	 *             if no subject found
	 * @throws RemoteException
	 */
	ISubject getSubject(String name) throws RemoteException, SubjectNotFound;

	/**
	 * List all subject create on the forum server, and subscribe client to
	 * receive new subject event and remove subject event
	 *
	 * @return all subject
	 * @throws RemoteException
	 */
	List<ISubject> listSubject(ICustomerForum client) throws RemoteException;

	/**
	 * Create new subject on forum server
	 *
	 * @param name
	 *            name of the new subject
	 * @return new Subject
	 * @throws RemoteException
	 * @throws SubjectAlreadyExist
	 *             if subject already exist
	 */
	ISubject createSubject(String name)
	        throws RemoteException, SubjectAlreadyExist;

	/**
	 * Remove subject on forum Server
	 *
	 * @param name
	 *            name of subject
	 * @return true if remove successful
	 * @throws RemoteException
	 * @throws SubscribeListeningException
	 *             if subject have subscriber
	 * @throws SubjectNotFound
	 *             if subject is not found
	 */
	boolean deleteSuject(String name) throws RemoteException,
	        SubscribeListeningException, SubjectNotFound;

}
