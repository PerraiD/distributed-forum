package org.alma.distributedforum.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IForumServer extends Remote {

	ISubject ObtainSubject(String nom) throws RemoteException;

	List<ISubject> ListSubject() throws RemoteException;

}