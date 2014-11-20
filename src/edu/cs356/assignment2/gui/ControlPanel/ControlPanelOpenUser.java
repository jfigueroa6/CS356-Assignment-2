package edu.cs356.assignment2.gui.ControlPanel;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.cs356.assignment2.gui.AdminControlPanel;
import edu.cs356.assignment2.service.TwitterService;

@SuppressWarnings("serial")
public class ControlPanelOpenUser extends JPanel {
	private static ControlPanelOpenUser instance = null;	/**Holds static reference to an instance of this class*/
	
	private JButton openUserView = new JButton("Open User View");
	
	private AdminControlPanel acpSingleton;
	private TwitterService service;
	//=========================================================
	// Constructor
	//=========================================================
	private ControlPanelOpenUser() {
		acpSingleton = AdminControlPanel.getInstance();
		service = TwitterService.getInstance();
		
		setBackground(getBackground());
		setPreferredSize(new Dimension(AdminControlPanel.WIDTH / 2,
				AdminControlPanel.HEIGHT / 3));	//Takes up 1/3 of the ControlPanel
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Initialize openUserView ActionListener
		addListenerUserView();
		//Add Button
		initOpenUser();
	}
	
	//=========================================================
	// Methods
	//=========================================================
	private void addListenerUserView() {
		//TODO: Implement this
	}
	/**
	 * Get the ControlPanelOpenUser singleton instance. Uses lazy instantiation.
	 * @return	Instance of ControlPanelOpenUser.
	 */
	public static ControlPanelOpenUser getInstance() {
		if (instance == null)
			instance = new ControlPanelOpenUser();
		return instance;
	}
	
	/**
	 * Initializes the Open User View portion of ControlPanel.
	 */
	private void initOpenUser() {
		//Add buttons
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(openUserView);
		add(Box.createRigidArea(new Dimension(0, getPreferredSize().height - 50)));
	}
}
