package org.alma.distributedforum.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Subject extends UnicastRemoteObject implements ISubject,Serializable {
	
	/* 
	 * liste des pointeurs vers les abonnée
	 */
	
	private String name;
	private List<String> history;


	public Subject(String name) throws RemoteException {
    super(10000);

    this.name = name;
		this.history= new ArrayList<String>();
	}

	@Override
	public boolean broadcast() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean subscription() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unsubscribe() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public void putMessage(String message) throws RemoteException {
		this.history.add(message);
	}
	
	public List<String> getLastMessages(int n) {
		
		List<String> messages = new ArrayList<String>();
		
		if (n <= history.size()) {
			for (String aHistory : history) {
				messages.add(aHistory);
			}
		} else {
			return history;
		}
		
		return messages;
		
	}
	
	public List<String> getHistory() {
		return history;
	}

	public void setHistory(List<String> history) {
		this.history = history;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
