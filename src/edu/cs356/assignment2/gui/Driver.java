package edu.cs356.assignment2.gui;

import edu.cs356.assignment2.service.User;

public class Driver {

	public static void main(String[] args) {
		User a = new User("22");
		User b = new User("23");
		
		System.out.println(a.getNewsFeed());
		b.addObserver(a);
		a.addObserver(b);
		b.postTweet("Hello");
		a.postTweet("Hello B");
		b.postTweet("How are you?");
		a.postTweet("Awesome");
		System.out.println(b.getNewsFeed());
		a.deleteObserver(b);
		System.out.println(b.getNewsFeed());

	}

}
