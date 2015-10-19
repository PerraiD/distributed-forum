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

            Integer serverPort;
            try {
                serverPort = Integer.valueOf(System.getProperty("server.port"));
            } catch (NumberFormatException e) {
                serverPort = IForumServer.SERVER_PORT;
            }

            String hostName = System.getProperty("server.hostname",
                    InetAddress.getLocalHost().getHostName());

            Registry registry = LocateRegistry.createRegistry(serverPort);

            IForumServer forum = new ForumServer(serverPort);
            registry.rebind(hostName, forum);

            System.out
                    .println("Server started : " + hostName + ":" + serverPort);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
