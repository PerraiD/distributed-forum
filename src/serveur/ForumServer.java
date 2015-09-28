package serveur;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;



public class ForumServer extends UnicastRemoteObject  implements IForumServer, Serializable {

		
		private ArrayList<ISubject> subjectList;
		
		protected ForumServer() throws RemoteException {
			subjectList=new ArrayList<ISubject>();
			
			/** some subjectList for the moment**/
			
			subjectList.add(new Subject("Sport"));
			subjectList.add(new Subject("Musique"));
			subjectList.add(new Subject("Art"));
			
		}
		
	

		@Override
		public ISubject ObtainSubject(String name) {
			
			for(ISubject s : subjectList){
				if(s.getName().equals(name)){
					return s;
				}
			}
			return null;
		}
		 

		
		
		
		
}