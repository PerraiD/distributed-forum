package org.alma.distributedforum.server;

import org.alma.distributedforum.server.exception.SubjectAlreadyExist;
import org.alma.distributedforum.server.exception.SubjectNotFound;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface of a distributed forum server
 *
 * @author dralagen
 */
public interface IForumServer extends Remote {

	/**
	 * Search on forum server the subject on name theme
	 *
	 * @param name name of subject
	 * @return the subject
	 * @throws SubjectNotFound if no subject found
	 * @throws RemoteException
	 */
	ISubject getSubject(String name) throws RemoteException, SubjectNotFound;

	/**
	 * List all subject create on the forum server
	 *
	 * @return all subject
	 * @throws RemoteException
	 */
	List<ISubject> ListSubject() throws RemoteException;

	/**
	 * Create new subject on forum server
	 *
	 * @param name name of the new subject
	 * @return new Subject
	 * @throws RemoteException
	 * @throws SubjectAlreadyExist
	 */
	ISubject createSubject(String name) throws RemoteException, SubjectAlreadyExist;

}