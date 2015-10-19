package org.alma.distributedforum.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.alma.distributedforum.server.ISubject;

public interface ICustomerForum extends Remote {

    public void newSubject(ISubject subject) throws RemoteException;

    public void removeSubject(ISubject subject) throws RemoteException;
}
