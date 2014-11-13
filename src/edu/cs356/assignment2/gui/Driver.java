package edu.cs356.assignment2.gui;

import edu.cs356.assignment2.service.TwitterService;

public class Driver {

	public static void main(String[] args) {
		TwitterService.getInstance().addGroup("Test");
		TwitterService.getInstance().addGroup("Test2", "Test");
		
		TwitterService.getInstance().addUser("Bob");
		TwitterService.getInstance().addUser("Alice", "Test");
		
		TwitterService.getInstance().removeGroup("Test");
		TwitterService.getInstance().removeUser("Bob");
	}

}
