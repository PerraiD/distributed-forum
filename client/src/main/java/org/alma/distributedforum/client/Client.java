package org.alma.distributedforum.client;

import org.alma.distributedforum.server.IForumServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created on 9/29/15.
 *
 * @author dralagen
 */
public class Client {

  public static void main(String[] args) {

    try {
      final Registry registry = LocateRegistry.getRegistry(10000);

      String[] servers = registry.list();

      if (servers.length == 0) {
        throw new Exception("No Server Found");
      }

      if (servers.length == 1) {
        showMenu((IForumServer) registry.lookup(servers[0]));
      }else {

        final JDialog dialogSelectServer = new JDialog();
        JPanel dialogPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JComboBox<String> allServer = new JComboBox<>(servers);
        JButton btnSelectServer = new JButton("Select");

        btnSelectServer.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            try {
              IForumServer forumServer = (IForumServer) registry
                      .lookup((String) allServer.getSelectedItem());

              showMenu(forumServer);
              dialogSelectServer.setVisible(false);
            } catch (RemoteException | NotBoundException e1) {
              e1.printStackTrace();
            }
          }
        });

        dialogPanel.add(allServer);
        dialogPanel.add(btnSelectServer);

        dialogSelectServer.add(dialogPanel);
        dialogSelectServer.setSize(300,50);
        dialogSelectServer.setVisible(true);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void showMenu(IForumServer server) throws RemoteException {
    ViewMenu vm = new ViewMenu(server);
    vm.showMenu();
  }
}
