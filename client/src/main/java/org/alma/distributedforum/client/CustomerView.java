package org.alma.distributedforum.client;

import org.alma.distributedforum.server.ISubject;

import java.io.Serializable;
import java.io.ObjectInputStream.GetField;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerView extends UnicastRemoteObject implements ICustomerView,Serializable {

	private ISubject subject;
	private ViewForum viewforum;

	public CustomerView(ISubject subject, ViewForum viewForum) throws RemoteException {
		super();
		this.viewforum= viewForum;
		this.subject = subject;

		init();
	}

	private void init() {
		try {
			subject.subscribe(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void show(String message) throws RemoteException {
		viewforum.appendMessage(message);
	}


	public void unsubscribe() {
		try {

			subject.unsubscribe(this);

			UnicastRemoteObject.unexportObject(this, true);

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void writeMessage(String message) {
		try {
			subject.sendMessage(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public ISubject getSubject() {
		return subject;
	}
	
}
