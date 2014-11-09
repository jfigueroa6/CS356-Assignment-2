package edu.cs356.assignment2.service;

public class Tweet {
	private String id;			/**ID of user that wrote tweet*/
	private String tweet;		/**Tweet*/
	private static int charLimit = 140;	/**Character limit for tweets defaulted to 140*/
	
	//=========================================================
	// Constructor
	//=========================================================
	public Tweet(String id, String tweet) {
		this.id = id;
		//Only allow a message with a character limit of charLimit
		if (tweet.length() > charLimit)
			this.tweet = tweet.substring(0, charLimit);
		else
			this.tweet = tweet;
	}
	
	//=========================================================
	// Methods
	//=========================================================
	public String getID() {
		return id;
	}
	
	public String getTweet() {
		return tweet;
	}
	
	public static int getCharLimit() {
		return charLimit;
	}
	
	public static void setCharLimit(int newLimit) {
		if (newLimit > 0)
			charLimit = newLimit; 
	}
	
	public String toString() {
		return id + ": " + tweet;
	}
}
