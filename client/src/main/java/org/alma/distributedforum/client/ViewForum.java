package org.alma.distributedforum.client;

import org.alma.distributedforum.server.ISubject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

public class ViewForum {
	private CustomerView customer;
	private ForumCustomer forumCustomer;

	private JTextArea text;
	private JTextField textEnter;

	public ViewForum(ISubject subjectObj,ForumCustomer fc)  {
		try {
			forumCustomer = fc;
			customer = new CustomerView(subjectObj,this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void showForum() throws HeadlessException, RemoteException{
		
		/* definition of the window. */
		JFrame window = new JFrame(customer.getSubject().getName());
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(800, 500);
		window.setLocationRelativeTo(null);

		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				customer.unsubscribe();
				super.windowClosing(e);
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.BOTH;
		
		/* ipady permet de savoir où on place le composant s'il n'occupe pas la totalité de l'espace disponnible */
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;

		gc.weightx = 2;	
		gc.weighty = 2;
		
		/* layout constraint of the textArea  */
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 3;
		gc.gridheight= 2;
		
		text = new JTextArea();
		text.setEditable(false);
		
		customer.getHistory();

		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		/*adding the textArea to the panel  with layout constraint*/		
		panel.add(scroll, gc);

		/* layout constraint of the textFields  */
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 1;
		gc.gridheight= 1;
		
		/*fill the raw with the textField*/
		gc.fill = GridBagConstraints.HORIZONTAL;
		
		textEnter = new JTextField();
		/*adding the textField to the panel with layout constraint*/
		panel.add(textEnter, gc);
		
		/*changing the fill  to avoid a big button*/
		gc.ipady = GridBagConstraints.NONE;
		gc.fill = GridBagConstraints.NONE;
		
		
		/* layout constraint of the button  */
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 0;
		JButton sendBtn = new JButton("Send");
		
		/*listener on the sendButton  */
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = forumCustomer.getNom() + " : " + textEnter.getText();
				textEnter.setText("");
				//broadcasting of the new message to the forum serveur.
				customer.writeMessage(value);
			}
		});
		
		/*adding the button to the panel*/
		panel.add(sendBtn, gc);
		
		window.add(panel);
		window.setVisible(true);
		
	}
	
	public void appendMessage(String message){
		
		text.setText(text.getText()+"\n"+message);
	    text.repaint();
	    
	}


}
