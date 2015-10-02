package org.alma.distributedforum.client;

import java.awt.Desktop.Action;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.Oneway;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.alma.distributedforum.server.IForumServer;
import org.alma.distributedforum.server.ISubject;
import org.alma.distributedforum.server.exception.SubjectAlreadyExist;
import org.alma.distributedforum.server.exception.SubjectNotFound;

public class ViewMenu {
	
	private JFrame window;
	private JButton sendBtn;
	private JButton createSubBtn;
	private JTextArea text;
	private JTextField textEnter;
	private JPanel panel;
	private JScrollPane scroll;	
	private List<String> subjectUser;
	private IForumServer forumServer;
	private JComboBox<String> subjectComboB;
	private JLabel createSubLabel;
	
	public  ViewMenu(IForumServer forumServer) {
		this.subjectUser = new ArrayList<String>();
		this.forumServer=forumServer;
		 
	}
	
	public void showMenu() throws RemoteException {
		
		List<ISubject> subjects= this.forumServer.listSubject();
	    String[] subjectNames = new String[subjects.size()];
	      
	    for(int i=0;i<subjects.size();i++){
	    	  subjectNames[i]= subjects.get(i).getName();
	    }
		
		window = new JFrame("Distributed-Forum");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(300, 250);
		window.setLocationRelativeTo(null);
		
		
		panel = new JPanel();	
		panel.setLayout(new GridBagLayout());
					
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.BOTH;
		
		/* ipady permet de savoir où on place le composant s'il n'occupe pas la totalité de l'espace disponnible */
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;

		gc.weightx = 2;
		gc.weighty = 4;
		
		/*changing the fill */
		gc.ipady = GridBagConstraints.NONE;
		gc.fill = GridBagConstraints.NONE;
		
		/*layout constraint of the userName Label */
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight= 1;
		
		JLabel nameLab = new JLabel("User name :");
		panel.add(nameLab, gc);
	
		/* layout constraint of the textFields  */
		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight= 1;
	
		final JTextField textEnter = new JTextField(10);
		/*adding the textField to the panel with layout constraint*/
		panel.add(textEnter, gc);
						
		/*layout constraint of the Subscribing Label */
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight= 1;
		
		JLabel subscribingLabel = new JLabel("Subscribe to :");
		panel.add(subscribingLabel, gc);
	
		/* layout constraint of the Combo box  */
		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight= 1;
			
		subjectComboB = new JComboBox<String>(subjectNames);
		
		/*adding the textField to the panel with layout constraint*/
		panel.add(subjectComboB, gc);
		
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 1;
		createSubLabel = new JLabel("Or create one : ");
		panel.add(createSubLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.gridwidth = 1;
		createSubBtn = new JButton("create subject");
		
		createSubBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try{
					if(!textEnter.getText().isEmpty()){
						JPanel diagPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
						final JDialog createSubDial= new JDialog(window);
						final JTextField newSubName= new JTextField("Enter subject name");
						
						JButton createSub = new JButton("create");
						
						
						createSub.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e)
							{	
								if(!newSubName.getText().isEmpty() && !newSubName.getText().equals("Enter subject name")){
									try {
										ISubject subjectObj = forumServer.createSubject(newSubName.getText());
										ForumCustomer fc = new ForumCustomer(textEnter.getText());
										ViewForum vf = new ViewForum(subjectObj,fc);
										vf.showForum();
										  
										window.setVisible(false);
										createSubDial.setVisible(false);
									} catch (RemoteException e1) {
										e1.printStackTrace();
									} catch (SubjectAlreadyExist e1) {
										e1.printStackTrace();
									}
								}
									
							}
						});
						
						
						diagPan.add(newSubName);
						diagPan.add(createSub);
						
						createSubDial.add(diagPan);
						createSubDial.setSize(200, 100);
						createSubDial.setVisible(true);
					}
				}catch(Exception ex){
					
				}
			}
		});
		
		panel.add(createSubBtn, gc);
			
		/* layout constraint of the button  */
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 0;
		sendBtn = new JButton("Connect to the discution !");
		
		
		/*listener on the sendButton */
		sendBtn.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {		  
			try{  
			  String userName = textEnter.getText();
			  if(!userName.isEmpty()){
				  String subject = subjectComboB.getSelectedItem().toString();
				  window.setVisible(false);
				  ISubject subjectObj = forumServer.getSubject(subject);
				  ForumCustomer fc = new ForumCustomer(userName);
				  ViewForum vf = new ViewForum(subjectObj,fc);
				  vf.showForum();
			  }
			}catch(RemoteException re){
				re.printStackTrace();
			}catch (SubjectNotFound e1) {
				e1.printStackTrace();
			}
		  }
		});
		
		/*adding the button to the panel*/
		panel.add(sendBtn, gc);	
		window.add(panel);
		window.setVisible(true);
	}
	
	
	
	

}
