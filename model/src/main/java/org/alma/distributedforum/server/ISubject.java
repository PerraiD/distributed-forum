package org.alma.distributedforum.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISubject extends Remote {

	boolean broadcast() throws RemoteException;

	boolean subscription() throws RemoteException;

	boolean unsubscribe () throws RemoteException;

	String getName() throws RemoteException;

}
