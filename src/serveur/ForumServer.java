package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ForumServer extends UnicastRemoteObject  implements IForumServer {

	
		
		private ArrayList<Subject> subjectList;
		
		protected ForumServer() throws RemoteException {
			super();
			
		}
		
	


		@Override
		public ISubject ObtainSubject(String nom) {
			// TODO Auto-generated method stub
			return null;
		}


		
		
		
		
}