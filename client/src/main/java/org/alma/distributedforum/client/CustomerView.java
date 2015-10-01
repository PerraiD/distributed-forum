package org.alma.distributedforum.client;

import org.alma.distributedforum.server.ISubject;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerView extends UnicastRemoteObject implements ICustomerView,Serializable {

	private ISubject subject;

	protected CustomerView(ISubject subject) throws RemoteException {
		super();

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
		System.out.println(message);
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
}
