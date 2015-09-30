package org.alma.distributedforum.client;

import org.alma.distributedforum.server.IForumServer;
import org.alma.distributedforum.server.ISubject;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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

      ICustomerView myView = new CustomerView();
      ISubject subject = forumServer.ObtainSubject("Art");
      subject.subscription(myView);

      subject.putMessage("Hello");

      subject.unsubscribe(myView);

      subject.putMessage("wold!");

      UnicastRemoteObject.unexportObject(myView, true);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
