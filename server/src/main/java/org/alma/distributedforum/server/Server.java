package org.alma.distributedforum.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created on 9/29/15.
 *
 * @author dralagen
 */
public class Server {
  public static void main(String[] args) {
    try {
      Registry registry = LocateRegistry.createRegistry(10000);

      IForumServer forum = new ForumServer();
      registry.rebind("forum",forum);

      System.out.println("Server started");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
