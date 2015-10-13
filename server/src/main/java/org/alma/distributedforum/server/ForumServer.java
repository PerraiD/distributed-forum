package org.alma.distributedforum.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.alma.distributedforum.server.exception.SubjectAlreadyExist;
import org.alma.distributedforum.server.exception.SubjectNotFound;
import org.alma.distributedforum.server.exception.SubscribeListeningException;

public class ForumServer extends UnicastRemoteObject implements IForumServer {

	private static final long serialVersionUID = 41305478188360920L;

	private List<ISubject> subjectList;

	protected ForumServer() throws RemoteException {
		super(IForumServer.SERVER_PORT);

		subjectList = new ArrayList<>();

		/** some subjectList for the moment **/

		subjectList.add(new Subject("Sport"));
		subjectList.add(new Subject("Musique"));
		subjectList.add(new Subject("Art"));

	}

	/**
	 * Find the subject with good name
	 *
	 * @param name
	 *            name of subject
	 * @return subject found
	 * @throws SubjectNotFound
	 *             if no subject have good name
	 * @throws RemoteException
	 */
	private ISubject findSubject(String name)
	        throws SubjectNotFound, RemoteException {
		for (ISubject s : subjectList) {
			if (s.getName().equals(name))
				return s;
		}

		throw new SubjectNotFound("Subject : " + name + " not found");
	}

	@Override
	public ISubject getSubject(String name)
	        throws SubjectNotFound, RemoteException {

		return findSubject(name);
	}

	@Override
	public List<ISubject> listSubject() {
		return subjectList;
	}

	@Override
	public ISubject createSubject(String name)
	        throws RemoteException, SubjectAlreadyExist {
		ISubject newSubject;

		try {
			newSubject = findSubject(name);
			throw new SubjectAlreadyExist(newSubject);
		} catch (SubjectNotFound subjectNotFound) {
			newSubject = new Subject(name);
			subjectList.add(newSubject);
		}

		return newSubject;
	}

	@Override
	public boolean deleteSuject(String name) throws RemoteException,
	        SubscribeListeningException, SubjectNotFound {
		Subject removeSubject = (Subject) findSubject(name);

		if (removeSubject.haveSucribe())
			throw new SubscribeListeningException();

		return subjectList.remove(removeSubject);
	}
}