package org.alma.distributedforum.client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.alma.distributedforum.server.IForumServer;

/**
 * Created on 9/29/15.
 *
 * @author dralagen
 */
public class Client {
	static JDialog dialogSelectServer;

	public static void main(String[] args) {
		try {

			selectServer(null);

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

		JButton dialogValid = new JButton("Connect");
		dialogValid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					selectServer(input_serverName.getText());
					dialogInputServer.setVisible(false);
				} catch (RemoteException | NotBoundException ignore) {
				}
			}
		});

		dialogPanel.add(dialogValid);

		dialogInputServer.add(dialogPanel);
		dialogInputServer.setSize(400, 55);
		dialogInputServer.setVisible(true);
	}

	private static void selectServer(String host)
	        throws RemoteException, NotBoundException {

		Registry registry = LocateRegistry.getRegistry(host,
		        IForumServer.SERVER_PORT);

		String[] servers = registry.list();

		showMenu(host, servers[0]);
	}

	public static void showMenu(String host, String lookup)
	        throws RemoteException {
		ViewMenu vm = new ViewMenu(host, lookup);
		vm.showMenu();
	}
}
