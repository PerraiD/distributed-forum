package org.alma.distributedforum.server;

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

	@Override
	public ISubject ObtainSubject(String name) {

		for(ISubject s : subjectList){
			if(((Subject)s).getName().equals(name)){
				return s;
			}
		}
		return null;
	}

	@Override
	public List<ISubject> ListSubject() {
		return subjectList;
	}
}