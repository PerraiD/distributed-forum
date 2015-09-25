package org.alma.distributedforum.serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;



public class ForumServer extends UnicastRemoteObject  implements IForumServer {

		
		private ArrayList<Subject> subjectList;
		
		protected ForumServer() throws RemoteException {
			subjectList=new ArrayList<Subject>();
			
			/** some subjectList for the moment**/
			
			subjectList.add(new Subject("Sport"));
			subjectList.add(new Subject("Musique"));
			subjectList.add(new Subject("Art"));
			
		}
		
	

		@Override
		public ISubject ObtainSubject(String name) {
			
			for(Subject s : subjectList){
				if(s.getName().equals(name)){
					return s;
				}
			}
			return null;
		}


		
		
		
		
}