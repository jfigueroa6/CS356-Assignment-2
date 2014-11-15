package edu.cs356.assignment2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import edu.cs356.assignment2.gui.Visitor.TreeViewVisitor;
import edu.cs356.assignment2.service.TwitterService;

@SuppressWarnings("serial")
public class AdminControlPanel extends JFrame {
	//Static Members
	private static AdminControlPanel instance = null;	/**Instance of this class*/
	private static final int HEIGHT = 480;	/**Preferred height for window*/
	private static final int WIDTH = 600;	/**Preferred width for window*/
	private static final int GAP = 10;		/**Gap between tree view and buttons/TextAreas*/
	
	//Swing Members
	private JScrollPane treeView = null;
	
	private TwitterService service = null;	/**Holds a reference to the TwitterService singleton*/
	//=========================================================
	// Constructor
	//=========================================================
	private AdminControlPanel() {
		super("Twitter Admin Control Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Perform TwitterService and window initialization
		service = TwitterService.getInstance();
		initializePanel();
		
		//Initialization complete so display the window.
		setVisible(true);
	}
	
	//=========================================================
	// Methods
	//=========================================================
	/**
	 * Since this class is a singleton, this provides a way to get
	 * the instance. This method does lazy instantiation.
	 * @return	Instance of this class.
	 */
	public static AdminControlPanel getInstance() {
		if (instance == null)
			instance = new AdminControlPanel();
		return instance;
	}
	
	/**
	 * Initializes the main panel. This panel handles the size of 
	 * the main window. This method also initializes the TreeView,
	 * buttons, and TextAreas.
	 */
	private void initializePanel() {
		JPanel panel = new JPanel();
		
		//Set default size, background color, and layout
		panel.setBackground(Color.DARK_GRAY);
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(new BorderLayout(GAP, GAP));	//Border layout has NORTH, SOUTH, EAST, WEST, CENTER areas.
		
		//TODO:Initialize TextAreas, and Buttons
		//Create the tree and add it to the panel.
		updateTreeView();
		panel.add(treeView, BorderLayout.WEST);
		
		
		//Add panel to ACP
		getContentPane().add(panel);
		pack();		//Resizes to the preferred size
	}
	
	private void updateTreeView() {
		//Send the visitor on its super secret mission to create a tree from the user and group ID's
		TreeViewVisitor visitor = new TreeViewVisitor(); 
		service.accept(visitor);
		//Mission complete! Create the tree from the root stored in visitor, add scrollbars to it, and assign it to treeView
		treeView = new JScrollPane(new JTree(visitor.getRoot()));
		treeView.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
	}
}
