package edu.cs356.assignment2.gui.ControlPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.cs356.assignment2.gui.AdminControlPanel;
import edu.cs356.assignment2.gui.Visitor.UserTotalVisitor;
import edu.cs356.assignment2.service.TwitterService;

@SuppressWarnings("serial")
public class ControlPanelShowControl extends JPanel {
	private static ControlPanelShowControl instance = null;	/**Holds static reference to an instance of this class*/
	
	private JButton showGroupTotal = new JButton("Show Group Total"),
					showMsgTotal = new JButton("Show Message Total"),
					showPosPerc = new JButton("Show Positive Percentage"),
					showUserTotal = new JButton("Show User Total");
	
	private AdminControlPanel acpSingleton;
	private TwitterService service;
	//=========================================================
	// Constructor
	//=========================================================
	private ControlPanelShowControl() {
		acpSingleton = AdminControlPanel.getInstance();
		service = TwitterService.getInstance();
		
		setBackground(getBackground());
		setPreferredSize(new Dimension(AdminControlPanel.WIDTH / 2,
				AdminControlPanel.HEIGHT / 3));	//Takes up 1/3 of the ControlPanel
		setLayout(new GridLayout(2, 2, 5, 5));
		
		//Initialize the ActionListeners
		initActionListeners();
		//Add Buttons
		initShowButtons();
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
	 * Get the ControlPanelShowControl singleton instance. Uses lazy instantiation.
	 * @return	Instance of ControlPanelShowControl.
	 */
	public static ControlPanelShowControl getInstance() {
		if (instance == null)
			instance = new ControlPanelShowControl();
		return instance;
	}
	
	/**
	 * Initializes the ActionListeners for all of the buttons.
	 */
	private void initActionListeners() {
		addListenerShowUserTotal();	//Initialize showUserTotal ActionListener
		//addListenerShowGroupTotal();	//Initialize showGroupTotal ActionListener
		//addListenerShowMsgTotal();	//Initialize showMsgTotal ActionListener
		//addListenerShowPosPerc();	//Initialize showPosPerc ActionListener
	}
	
	/**
	 * Initialize the buttons for the panel.
	 */
	private void initShowButtons() {
		//Add the buttons to the panel
		add(showUserTotal);
		add(showGroupTotal);
		add(showMsgTotal);
		add(showPosPerc);
	}
}
