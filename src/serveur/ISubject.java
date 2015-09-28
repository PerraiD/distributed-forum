package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ISubject extends Remote{
	
	
	public void putMessage(String message) throws RemoteException;
	
	public ArrayList<String> getLastMessages(int n);
	
	public String getName(); 
	
	public boolean broadcast() throws RemoteException;
	
	public boolean subscription()throws RemoteException;
	
	public boolean unsubscribe()throws RemoteException;
	
}
