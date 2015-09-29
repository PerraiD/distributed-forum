package org.alma.distributedforum.serveur;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

<<<<<<< HEAD:src/serveur/Subject.java
public class Subject implements ISubject,Serializable{
	
	/* 
	 * liste des pointeurs vers les abonnée
	 */
	
	
	
=======
public class Subject implements ISubject {

	/*
	 * list of pointers to all subscriber
	 */
>>>>>>> f08f183bda032323e9b1b4b8ec13c762283a7f93:server/src/main/java/org/alma/distributedforum/serveur/Subject.java
	private ArrayList<String> history;

	private String name;
<<<<<<< HEAD:src/serveur/Subject.java
	
	public Subject(String name) {
		this.name = name;	
=======

	public Subject(String name){
		this.name = name;
>>>>>>> f08f183bda032323e9b1b4b8ec13c762283a7f93:server/src/main/java/org/alma/distributedforum/serveur/Subject.java
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
<<<<<<< HEAD:src/serveur/Subject.java
	
	
	public void putMessage(String message) throws RemoteException {
		this.history.add(message);
	}
	
	public ArrayList<String> getLastMessages(int n) {
		
		ArrayList<String> messages = new ArrayList<String>();
		
		if (n <= history.size()) {
			for(int i=0;i<history.size();i++) {
				messages.add(history.get(i));
			}
		} else {
			return history;
		}
		
		return messages;
		
	}
	
=======

	public void pullMessage(String message) throws RemoteException {
		this.history.add(message);
	}

>>>>>>> f08f183bda032323e9b1b4b8ec13c762283a7f93:server/src/main/java/org/alma/distributedforum/serveur/Subject.java
	public ArrayList<String> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<String> history) {
		this.history = history;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
