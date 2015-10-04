package org.alma.distributedforum.server;

import java.net.InetAddress;
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
      Registry registry = LocateRegistry.createRegistry(IForumServer.SERVER_PORT);

      IForumServer forum = new ForumServer();
      String hostName = InetAddress.getLocalHost().getHostName();
      registry.rebind(hostName,forum);

      System.out.println("Server started : " + hostName);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
