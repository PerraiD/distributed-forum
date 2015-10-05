package org.alma.distributedforum.client;

import org.alma.distributedforum.server.IForumServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.ConnectException;
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
  static JDialog dialogSelectServer;

  public static void main(String[] args) {
    try {

      selectServer( LocateRegistry.getRegistry(IForumServer.SERVER_PORT));

    } catch (ConnectException e) {
      inputServer();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void inputServer() {
    final JDialog dialogInputServer = new JDialog();
    dialogInputServer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    JPanel dialogPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JLabel label_serverName = new JLabel("Server : ");

    dialogPanel.add(label_serverName);


    final JTextField input_serverName = new JTextField(15);
    dialogPanel.add(input_serverName);

    JButton dialogValid = new JButton("Connect");
    dialogValid.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          selectServer(LocateRegistry.getRegistry(input_serverName.getText(), IForumServer.SERVER_PORT));
          dialogInputServer.setVisible(false);
        } catch (RemoteException | NotBoundException ignore) {
        }
      }
    });

    dialogPanel.add(dialogValid);

    dialogInputServer.add(dialogPanel);
    dialogInputServer.setSize(400,55);
    dialogInputServer.setVisible(true);
  }

  private static void selectServer(final Registry registry) throws RemoteException, NotBoundException {
    String[] servers = registry.list();

    if (servers.length == 1) {
      showMenu((IForumServer) registry.lookup(servers[0]));
    } else {
      dialogSelectServer = new JDialog();
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
  }

  public static void showMenu(IForumServer server) throws RemoteException {
    ViewMenu vm = new ViewMenu(server);
    vm.showMenu();
  }
}
