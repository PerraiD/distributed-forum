package org.alma.distributedforum.client;

import org.alma.distributedforum.server.IForumServer;
import org.alma.distributedforum.server.ISubject;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/29/15.
 *
 * @author dralagen
 */
public class Client {

  public static void main(String[] args) {

    try {
      Registry registry = LocateRegistry.getRegistry(10000);
      IForumServer forumServer = (IForumServer) registry.lookup("forum");
    
      
      ViewMenu vm = new ViewMenu(forumServer);
      vm.showMenu();
      

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
