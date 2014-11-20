package edu.cs356.assignment2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import edu.cs356.assignment2.gui.ControlPanel.ControlPanel;
import edu.cs356.assignment2.gui.Visitor.TreeViewVisitor;
import edu.cs356.assignment2.service.TwitterService;

@SuppressWarnings("serial")
public class AdminControlPanel extends JFrame {
	//Static Members
	private static AdminControlPanel instance = null;	/**Instance of this class*/
	public static final int HEIGHT = 480;	/**Preferred height for window*/
	public static final int WIDTH = 600;	/**Preferred width for window*/
	private static final int GAP = 5;		/**Gap between tree view and buttons/TextAreas*/
	
	//Swing Members
	private JPanel mainPanel = null;
	private JScrollPane treeView = null;
	private ControlPanel control = null;
	
	//Class Members
	private TwitterService service = null;	/**Holds a reference to the TwitterService singleton*/
	private String selectedID = "root";		/**Holds the ID selected on tree. Default of root just in case none is selected*/
	//=========================================================
	// Constructor
	//=========================================================
	private AdminControlPanel() {
		super("Twitter Admin Control Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Perform TwitterService initialization
		service = TwitterService.getInstance();
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
	
	public String getSelectedID() {
		return selectedID;
	}
	
	/**
	 * Gets the index where the tree view is stored in the mainPanel components array.
	 * @return	Index of tree view or -1 if it does not exist.
	 */
	private int getTreeViewIndex() {
		Component[] comp = mainPanel.getComponents();
		for (int i = 0; i < comp.length; i++) {
			if (comp[i] instanceof JScrollPane)
				return i;
		}
		return -1;
	}
	
	/**
	 * Initializes the main panel. This panel handles the size of 
	 * the main window. This method also initializes the TreeView,
	 * buttons, and TextAreas.
	 */
	private void initializePanel() {
		mainPanel = new JPanel();
		
		//Set default size, background color, and layout
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		mainPanel.setLayout(new BorderLayout(GAP, GAP));	//Border layout has NORTH, SOUTH, EAST, WEST, CENTER areas.
		
		//TODO:Initialize TextAreas, and Buttons
		//Create the tree and add it to the panel.
		updateTreeView();
		//TODO: Might have to move this to updateTreeView
		control = ControlPanel.getInstance();
		mainPanel.add(control, BorderLayout.EAST);
		
		
		//Add panel to ACP
		getContentPane().add(mainPanel);
		pack();		//Resizes to the preferred size
	}
	
	/**
	 * Initializes all of the swing objects and displays the main ACP window.
	 */
	public void startACP() {
		initializePanel();
		
		//Initialization complete so display the window.
		setVisible(true);
	}
	
	/**
	 * Updates the ACP View. In particular, it updates the tree view to display new users and
	 * groups. It also resets selectedID to root
	 */
	public void updateACPView() {
		mainPanel.remove(treeView);
		updateTreeView();
		paint(getGraphics());
		selectedID = "root";
	}
	
	/**
	 * Updates the tree view by sending a visitor to the TwitterService. Once it returns, the tree is
	 * developed from the result, and it is stored into the tree view. The size is set to half the width
	 * of the parent panel.
	 * @param initialize	If true, then this call is part of init, and won't remove treeView from mainPanel.
	 */
	private void updateTreeView() {
		//Send the visitor on its super secret mission to create a tree from the user and group ID's
		TreeViewVisitor visitor = new TreeViewVisitor(); 
		service.accept(visitor);
		
		//TODO: Change the icons for the tree. Groups that do not have any children look like users.
		
		//Set the selection mode and the selection listener.
		JTree tree = new JTree(visitor.getRoot());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);	//Can only select one item in the tree
		tree.addTreeSelectionListener(new TreeListener());
		
		//Mission complete! Create the tree from the root stored in visitor, add scrollbars to it, and assign it to treeView
		treeView = new JScrollPane(tree);
		treeView.setPreferredSize(new Dimension((WIDTH / 3) - GAP, HEIGHT));
		mainPanel.add(treeView, BorderLayout.WEST);
	}
	
	//=========================================================
	// Private Classes
	//=========================================================
	/**
	 * Listener for the treeview.
	 */
	private class TreeListener implements TreeSelectionListener {
		/**
		 * When an item on the treeview is selected, the ID of this selection is stored
		 * in selectedID.
		 */
		@Override
		public void valueChanged(TreeSelectionEvent arg0) {
			selectedID = arg0.getPath().getLastPathComponent().toString();
		}
	}
}
