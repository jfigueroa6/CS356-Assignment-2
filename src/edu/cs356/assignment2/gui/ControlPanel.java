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

@SuppressWarnings("serial")
public class ControlPanel extends JPanel implements ActionListener{
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
	//=========================================================
	// Constructor
	//=========================================================
	private ControlPanel() {
		acpSingleton = AdminControlPanel.getInstance();
		
		//First set up the panel.
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension((AdminControlPanel.WIDTH * 2 / 3) - GAP, AdminControlPanel.HEIGHT));
		setLayout(new GridLayout(3, 1, GAP, 0));	//Forces layout to be 3 row and 1 column
		
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
	 * Get the ControlPanel singleton instance. Uses lazy instantiation.
	 * @return	Instance of ControlPanel.
	 */
	public static ControlPanel getInstance() {
		if (instance == null)
			instance = new ControlPanel();
		return instance;
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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean result = true;
		JOptionPane msg;
		
		//Find out which button called the listener
		//if (arg0.getSource() == addUser)
	}
}
