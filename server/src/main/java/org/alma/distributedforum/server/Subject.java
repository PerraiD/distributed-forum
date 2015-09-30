package org.alma.distributedforum.server;

import org.alma.distributedforum.client.ICustomerView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Subject extends UnicastRemoteObject implements ISubject,Serializable {
	
	/* 
	 * liste des pointeurs vers les abonnée
	 */
	
	private String name;
	private List<String> history;

	private Set<ICustomerView> viewers;

	public Subject(String name) throws RemoteException {
		super(10000);

		this.name = name;
		history = new ArrayList<String>();
		viewers = new LinkedHashSet<ICustomerView>();
	}

	public void broadcast(String message) {
		// TODO Auto-generated method stub
		System.out.println("broadcast");
		for (ICustomerView view : viewers) {
			try {
				view.show(message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean subscription(ICustomerView view) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Subcribe");
		return viewers.add(view);
	}

	@Override
	public boolean unsubscribe(ICustomerView view) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("UnSubcribe");
		return viewers.remove(view);
	}

	@Override
	public void putMessage(String message) throws RemoteException {
		System.out.println("receive message : " + message);
		history.add(message);
		broadcast(message);
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
