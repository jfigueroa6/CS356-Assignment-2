package edu.cs356.assignment2.gui.ControlPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.cs356.assignment2.gui.AdminControlPanel;
import edu.cs356.assignment2.gui.Visitor.UserTotalVisitor;
import edu.cs356.assignment2.service.TwitterService;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel{
	private static ControlPanel instance = null;
	private static int GAP = 5;
	private JButton openUserView = new JButton("Open User View"),
					showGroupTotal = new JButton("Show Group Total"),
					showMsgTotal = new JButton("Show Message Total"),
					showPosPerc = new JButton("Show Positive Percentage"),
					showUserTotal = new JButton("Show User Total");
	
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
		//Initialize Open user view
		initOpenUser();
		//Initialize Show portion
		initShowButtons();
		
		//Add Button panels to this Main panel
		add(ControlPanelUserControl.getInstance());
	}
	
	//=========================================================
	// Methods
	//=========================================================
	/**
	 * Adds an ActionListener to the showUserTotal button which counts the number of users. It then displays
	 * the result in a dialog window.
	 */
	private void addListenerShowUserTotal() {
		showUserTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserTotalVisitor v = new UserTotalVisitor();
				//Count the number of users and display dialog box with results
				service.accept(v);
				JOptionPane.showMessageDialog(acpSingleton, "Number of Users: " + v.getUserTotal(), "User Count"
						, JOptionPane.INFORMATION_MESSAGE);
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
		//addListenerUserView();	//Initialize openUserView ActionListener
		addListenerShowUserTotal();	//Initialize showUserTotal ActionListener
		//addListenerShowGroupTotal();	//Initialize showGroupTotal ActionListener
		//addListenerShowMsgTotal();	//Initialize showMsgTotal ActionListener
		//addListenerShowPosPerc();	//Initialize showPosPerc ActionListener
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
