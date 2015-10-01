package org.alma.distributedforum.client;

import org.alma.distributedforum.server.IForumServer;
import org.alma.distributedforum.server.ISubject;

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

      ISubject subject = forumServer.getSubject("Art");

      CustomerView myView = new CustomerView(subject);

      myView.writeMessage("Hello");

      myView.unsubscribe();

      myView.writeMessage("wold!");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
