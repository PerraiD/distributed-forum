package serveur;

import java.rmi.Remote;

public interface IForumServer extends Remote {
		
	
	
	 public ISubject ObtainSubject(String nom);
	 
}