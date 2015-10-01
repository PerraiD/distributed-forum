package org.alma.distributedforum.server;

import org.alma.distributedforum.client.ICustomerView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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
		viewers = Collections.synchronizedSet(new LinkedHashSet<ICustomerView>());
	}

	/**
	 * Send the message at all viewers
	 *
	 * @param message message sent
	 */
	public void broadcast(String message) {
		// TODO Auto-generated method stub
		System.out.println("broadcast");
		Collection<ICustomerView> oldViewer = new LinkedList<ICustomerView>();
		for (ICustomerView view : viewers) {
			try {
				view.show(message);
			} catch (RemoteException e) {
				oldViewer.add(view);
			}
		}

		viewers.removeAll(oldViewer);
	}

	@Override
	public boolean subscribe(ICustomerView view) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Subscribe");
		return viewers.add(view);
	}

	@Override
	public boolean unsubscribe(ICustomerView view) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("UnSubcribe");
		return viewers.remove(view);
	}

	@Override
	public void sendMessage(String message) throws RemoteException {
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

	@Override
	public String getName() {
		return name;
	}
}
