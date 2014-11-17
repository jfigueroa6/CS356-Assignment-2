package edu.cs356.assignment2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.cs356.assignment2.service.TwitterService;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel{
	private static ControlPanel instance = null;
	private static int GAP = 5;
	private JButton addUser = new JButton("Add User"),
					addGroup = new JButton("Add Group"),
					openUserView = new JButton("Open User View"),
					removeUser = new JButton("Remove User"),
					removeGroup = new JButton("Remove Group"),
					showGroupTotal = new JButton("Show Group Total"),
					showMsgTotal = new JButton("Show Message Total"),
					showPosPerc = new JButton("Show Positive Percentage"),
					showUserTotal = new JButton("Show User Total");
	private JTextArea userID = new JTextArea("User ID", 1, 20),	/**TextArea with only 1 row and limit of 20 characters*/
					groupID = new JTextArea("Group ID", 1, 20);	/**TextArea with only 1 row and limit of 20 characters*/
	
	private AdminControlPanel acpSingleton;
	private TwitterService service;
	//=========================================================
	// Constructor
	//=========================================================
	private ControlPanel() {
		acpSingleton = AdminControlPanel.getInstance();
		service = TwitterService.getInstance();
		
		//First set up the panel.
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension((AdminControlPanel.WIDTH * 2 / 3) - GAP, AdminControlPanel.HEIGHT));
		setLayout(new GridLayout(3, 1, GAP, 0));	//Forces layout to be 3 row and 1 column
		
		//Initialize action listeners for buttons
		initActionListeners();
		//Initialize add/remove user/group
		initAddRemove();
		//Initialize Open user view
		initOpenUser();
		//Initialize Show portion
		initShowButtons();
	}
	//=========================================================
	// Methods
	//=========================================================
	/**
	 * Adds an ActionListener to the addUser button which tells TwitterService to add a user.
	 * If an error occurs, a dialog window appears.
	 */
	private void addListenerAddUser() {
		addUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Gets the group selected for the user from acpSingleton, and gets the new user ID
				//from the userID TextArea. If the addUserfunction fails, then display a dialog message
				if (service.addUser(userID.getText(), acpSingleton.getSelectedID()))
					//Operation successful so repaint the tree.
					acpSingleton.updateACPView();
				else
					//addUser failed so display a message
					JOptionPane.showMessageDialog(acpSingleton, "Error adding user. Check if selected ID is a user or"
							+ " new ID already exists.", "Add User Error", JOptionPane.ERROR_MESSAGE);
				userID.setText("User ID");
			}
		});
	}
	
	/**
	 * Adds an ActionListener to the addGroup button which tells TwiterService to add a group.
	 * If an error occurs, a dialog window is shown.
	 */
	private void addListenerAddGroup() {
		addGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Gets the parent group selected for the group from acpSingleton, and gets the new group ID
				//from the groupID TextArea. If the addGroup function fails, then display a dialog message
				if (service.addGroup(groupID.getText(), acpSingleton.getSelectedID()))
					//Operation successful so repaint the tree.
					acpSingleton.updateACPView();
				else
					//addGroup failed so display a message
					JOptionPane.showMessageDialog(acpSingleton, "Error adding group. Check if selected ID is a user or"
							+ " new ID already exists.", "Add Group Error", JOptionPane.ERROR_MESSAGE);
				groupID.setText("Group ID");
			}
		});
	}
	
	/**
	 * Adds an ActionListener to the removeUser button which tells TwitterService to remove a user.
	 * If an error occurs, a dialog window is shown.
	 */
	private void addListenerRemoveUser() {
		removeUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Get the selected User ID from acpSingleton. If the removeUser method fails,
				//then a dialog message will be displayed.
				if (service.removeUser(acpSingleton.getSelectedID()))
					//Operation successful so repaint the tree
					acpSingleton.updateACPView();
				else
					//removeUser failed so display a message
					JOptionPane.showMessageDialog(acpSingleton, "Error removing ID: " 
							+ acpSingleton.getSelectedID() + ". Make sure it is a user ID.", "Remove User Error"
							, JOptionPane.ERROR_MESSAGE);
				userID.setText("User ID");	//BUG: Doing this because for some reason tree view won't reappear
			}
		});
	}
	
	/**
	 * Adds an ActionListener to the removeGroup button which tells TwitterService to remove a group.
	 * If an error occurs, a dialog window is shown.
	 */
	private void addListenerRemoveGroup() {
		removeGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Get the selected Group ID from acpSingleton. If the removeGroup method fails,
				//then a dialog message will be displayed.
				if (service.removeGroup(acpSingleton.getSelectedID()))
					//Operation successful so repaint the tree
					acpSingleton.updateACPView();
				else
					//removeGroup failed so display a message
					JOptionPane.showMessageDialog(acpSingleton, "Error removing ID: " 
							+ acpSingleton.getSelectedID() + ". Make sure it is a group ID and not root.", "Remove Group Error"
							, JOptionPane.ERROR_MESSAGE);
				groupID.setText("Group ID");	//BUG: Doing this because for some reason tree view won't reappear
			}
		});
	}
	
	/**
	 * Get the ControlPanel singleton instance. Uses lazy instantiation.
	 * @return	Instance of ControlPanel.
	 */
	public static ControlPanel getInstance() {
		if (instance == null)
			instance = new ControlPanel();
		return instance;
	}
	
	/**
	 * Initializes the ActionListeners for all of the buttons.
	 */
	private void initActionListeners() {
		addListenerAddUser();	//Initialize addUser ActionListener
		addListenerAddGroup();	//Initialize addGroup ActionListener
		addListenerRemoveUser(); //Initialize removeUser actionListener
		addListenerRemoveGroup();	//Initialize removeGroup ActionListener
	}
	
	/**
	 * Initializes the Add/Remove portion of the ControlPanel.
	 */
	private void initAddRemove() {
		JPanel addRem = new JPanel();
		
		//Set up the panel
		addRem.setBackground(getBackground());
		addRem.setPreferredSize(new Dimension(getWidth(), getHeight() / 3));	//Takes up 1/3 of the ControlPanel
		addRem.setLayout(new GridLayout(3, 2, GAP, GAP));	//Forces layout to have 3 rows and 2 columns
		
		//Add the objects buttons and text areas for this panel
		addRem.add(userID);
		addRem.add(addUser);
		addRem.add(groupID);
		addRem.add(addGroup);
		addRem.add(removeUser);
		addRem.add(removeGroup);
		
		//Add the panel to ControlPanel
		add(addRem);
	}
	
	/**
	 * Initializes the Open User View portion of ControlPanel.
	 */
	private void initOpenUser() {
		JPanel openUser = new JPanel();
		
		//Set up panel
		openUser.setBackground(getBackground());
		openUser.setPreferredSize(new Dimension(getWidth(), getHeight() / 3));
		openUser.setLayout(new BoxLayout(openUser, BoxLayout.Y_AXIS));
		
		//Add button and add panel to ControlPanel
		openUser.add(Box.createRigidArea(new Dimension(0, 5)));
		openUser.add(openUserView);
		openUser.add(Box.createRigidArea(new Dimension(0, openUser.getPreferredSize().height - 50)));
		add(openUser);
	}
	
	private void initShowButtons() {
		JPanel showPanel = new JPanel();
		
		//Set up panel
		showPanel.setBackground(getBackground());
		showPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 3));
		showPanel.setLayout(new GridLayout(2, 2, GAP, GAP));
		
		//Add the buttons to the panel
		showPanel.add(showUserTotal);
		showPanel.add(showGroupTotal);
		showPanel.add(showMsgTotal);
		showPanel.add(showPosPerc);
		
		//Add panel to ControlPanel
		add(showPanel);
	}
}
