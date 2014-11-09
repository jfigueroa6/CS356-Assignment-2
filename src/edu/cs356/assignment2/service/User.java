package edu.cs356.assignment2.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

public class User extends Observable implements Observer {
	private List<String> followers,		/**List of ID's of followers*/
						 following;		/**List of ID's of users being followed*/
	private String id;					/**User id*/
	private Queue<String> newsFeed;		/**News feed containing user and following tweets*/
	
	//=========================================================
	// Constructor
	//=========================================================
	public User(String id) {
		super();
		this.id = id;
		followers = new LinkedList<String>();
		following = new LinkedList<String>();
		newsFeed = new LinkedList<String>();
	}
	
	//=========================================================
	// Data Members
	//=========================================================
	@Override
	public synchronized void addObserver(Observer arg0) {
		//First make sure you're not trying to follow yourself.
		//That's just narcissistic.
		if (this == arg0)
			return;
		
		followers.add(((User)arg0).id);
		((User)arg0).following.add(id);
		super.addObserver(arg0);
	}

	@Override
	public synchronized void deleteObserver(Observer arg0) {
		//You also can't unfollow yourself. If you try then
		//you need a psychiatrist.
		if (this == arg0)
			return;
		followers.remove(((User)arg0).id);
		((User)arg0).following.remove(id);
		super.deleteObserver(arg0);
	}
	
	@Override
	public synchronized void deleteObservers() {
		followers.clear();
		notifyObservers(-1); 		//-1 is used to tell observers to remove user from following list
		super.deleteObservers();
	}

	public List<String> getFollowers() {
		return followers;
	}
	
	public List<String> getFollowing() {
		return following;
	}
	
	public String getID() {
		return id;
	}
	
	public Queue<String> getNewsFeed() {
		return newsFeed;
	}
	
	public void postTweet(String tweet) {
		newsFeed.add(id + tweet);
		setChanged();
		notifyObservers(tweet);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		//	The passed arg1 is an integer so delete the observable object from
		// the following list. This is used when a user deletes all of its followers
		if (arg1 instanceof Integer)
			following.remove(((User)arg0).id);
		else {
			newsFeed.add(((User)arg0).id +": " + (String)arg1);
		}
	}
	
}
