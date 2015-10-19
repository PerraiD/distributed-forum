package org.alma.distributedforum.client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.alma.distributedforum.server.ISubject;

public class CustomerForum extends UnicastRemoteObject
        implements ICustomerForum, Serializable {

    private static final long serialVersionUID = 6566857971902548782L;

    private ViewMenu menu;

    public CustomerForum(ViewMenu menu) throws RemoteException {
        super();
        this.menu = menu;
    }

    @Override
    public void newSubject(ISubject subject) throws RemoteException {
        menu.appendSubject(subject);
    }

    @Override
    public void removeSubject(ISubject subject) throws RemoteException {
        menu.removeSubject(subject);

    }

}
