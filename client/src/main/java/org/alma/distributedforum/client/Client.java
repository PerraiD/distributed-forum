package org.alma.distributedforum.client;

import org.alma.distributedforum.server.IForumServer;

import javax.swing.*;
import java.awt.FlowLayout;
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

			selectServer(null, IForumServer.SERVER_PORT);

		} catch (ConnectException e) {
			inputServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void inputServer() {
		final JDialog dialogInputServer = new JDialog();
		dialogInputServer
		        .setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JPanel dialogPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JLabel label_serverName = new JLabel("Server : ");

		dialogPanel.add(label_serverName);

		final JTextField input_serverName = new JTextField(15);
		dialogPanel.add(input_serverName);

		JLabel label_serverPort = new JLabel("Port : ");

		dialogPanel.add(label_serverPort);

		final JTextField input_serverPort = new JTextField(String.valueOf(IForumServer.SERVER_PORT));
		dialogPanel.add(input_serverPort);


		JButton dialogValid = new JButton("Connect");
		dialogValid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					selectServer(input_serverName.getText(), Integer.parseInt(input_serverPort.getText()));
					dialogInputServer.setVisible(false);
				} catch (NumberFormatException e1) {
					try {
						selectServer(input_serverName.getText(), IForumServer.SERVER_PORT);
						dialogInputServer.setVisible(false);
					} catch (RemoteException | NotBoundException ignore) {}

				} catch (RemoteException | NotBoundException ignore) {
				}
			}
		});

		dialogPanel.add(dialogValid);

		dialogInputServer.add(dialogPanel);
		dialogInputServer.setSize(450, 60);
		dialogInputServer.setVisible(true);
	}

	private static void selectServer(String host, int port)
	        throws RemoteException, NotBoundException {

		Registry registry = LocateRegistry.getRegistry(host, port);

		String[] servers = registry.list();

		showMenu(host, port, servers[0]);
	}

	public static void showMenu(String host, int port, String lookup)
	        throws RemoteException {
		ViewMenu vm = new ViewMenu(host, port, lookup);
		vm.showMenu();
	}
}
