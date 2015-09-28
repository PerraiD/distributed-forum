package org.alma.distributedforum.serveur;

import java.rmi.Remote;

public interface IForumServer extends Remote {

	ISubject ObtainSubject(String nom);

}