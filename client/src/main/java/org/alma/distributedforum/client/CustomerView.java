package org.alma.distributedforum.client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.alma.distributedforum.server.ISubject;

public class CustomerView extends UnicastRemoteObject
        implements ICustomerView, Serializable {

	private static final long serialVersionUID = 7314939361682470120L;

	private ISubject subject;
	private ViewForum viewforum;

	public CustomerView(ISubject subject, ViewForum viewForum)
	        throws RemoteException {
		super();
		viewforum = viewForum;
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

	@Override
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

	public void getHistory() {
		List<String> history;
		try {
			history = subject.getHistory();

			for (String msg : history) {
				show(msg);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
