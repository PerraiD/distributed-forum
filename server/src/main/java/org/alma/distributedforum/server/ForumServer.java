package org.alma.distributedforum.server;

import org.alma.distributedforum.server.exception.SubjectAlreadyExist;
import org.alma.distributedforum.server.exception.SubjectNotFound;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ForumServer extends UnicastRemoteObject implements IForumServer {

	private List<ISubject> subjectList;

	protected ForumServer() throws RemoteException {
		super(10000);

		subjectList= new ArrayList<ISubject>();

		/** some subjectList for the moment**/

		subjectList.add(new Subject("Sport"));
		subjectList.add(new Subject("Musique"));
		subjectList.add(new Subject("Art"));

	}

	/**
	 * Find the subject with good name
	 * @param name name of subject
	 * @return subject found
	 * @throws SubjectNotFound if no subject have good name
	 */
	private ISubject findSubject(String name) throws SubjectNotFound {
		for(ISubject s : subjectList){
			if(((Subject)s).getName().equals(name)){
				return s;
			}
		}

		throw new SubjectNotFound("Subject : " + name +  " not found");
	}

	@Override
	public ISubject getSubject(String name) throws SubjectNotFound {

		return findSubject(name);
	}

	@Override
	public List<ISubject> listSubject() {
		return subjectList;
	}

	@Override
	public ISubject createSubject(String name) throws RemoteException, SubjectAlreadyExist {
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
}