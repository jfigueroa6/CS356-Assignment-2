package edu.cs356.assignment2.gui.ErrorWindow;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ErrorWindow extends JFrame {
	private static final int WIDTH = 100,	/**Holds width of message window*/
							HEIGHT = 50,	/**Holds height of message window*/
							GAP = 10;
	//=========================================================
	// Constructor
	//=========================================================
	public ErrorWindow(String error) {
		super("Error");
		//TODO:Might have to change this EXIT_ON_CLOSE to DO_NOTHING
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create Panel to hold message and OK button
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(new BorderLayout(GAP, GAP));
		
		//Add error label
		panel.add(new Label(error), BorderLayout.CENTER);
		
		//Add OK Button
		Button okButton = new Button("OK");
		okButton.addActionListener(new ButtonListener());
		
		
		//Show Window
		setVisible(true);
	}
	
	/**
	 * Closes the Error message window
	 */
	private void closeWindow() {
		setVisible(false);
	}
	//=========================================================
	// Private Classes
	//=========================================================
	/**
	 * Listener for the OK Button which closes the window.
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Calls the closeWindow() when the button is clicked.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			closeWindow();
		}
	}
}
