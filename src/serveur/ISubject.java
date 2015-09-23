package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISubject extends Remote{
	
	
	public boolean broadcast() throws RemoteException;
	
	public boolean subscription()throws RemoteException;
	
	public boolean unsubscribe()throws RemoteException;
	
}
