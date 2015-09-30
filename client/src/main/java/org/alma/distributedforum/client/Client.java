package org.alma.distributedforum.client;

import org.alma.distributedforum.server.IForumServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created on 9/29/15.
 *
 * @author dralagen
 */
public class Client {

  public static void main(String[] args) {
//    if (System.getSecurityManager() == null) {
//      System.setSecurityManager(new RMISecurityManager());
//    }

    try {
      Registry registry = LocateRegistry.getRegistry(10000);
      IForumServer forumServer = (IForumServer) registry.lookup("forum");
      System.out.println(forumServer.ObtainSubject("Art"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
