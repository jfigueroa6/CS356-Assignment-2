package edu.cs356.assignment2.service;

public class TwitterService {
	private static TwitterService instance = null;	/**TwitterService singleton*/
	private TwitterGroupTree treeSingleton;
	//=========================================================
	// Constructor
	//=========================================================
	/**
	 * Initializes the TwitterService. Is private since this class is a singleton.
	 */
	private TwitterService() {
		treeSingleton = TwitterGroupTree.getInstance();
	}
	
	//=========================================================
	// Methods
	//=========================================================
	public boolean addGroup(String groupID) {
		return treeSingleton.addGroup(groupID);
	}
	
	public boolean addGroup(String groupID, String parentID) {
		return treeSingleton.addGroup(groupID, parentID);
	}
	/**
	 * Static method to access the TwitterService singleton instance. Uses
	 * lazy instantiation, so memory is only used when this method is first
	 * called.
	 * @return	Instance of TwitterService
	 */
	public static TwitterService getInstance() {
		//If the instance has not been initialized
		//then initialize it.
		if (instance == null)
			instance = new TwitterService();
		return instance;
	}
	
	public boolean removeGroup(String groupID) {
		return treeSingleton.removeGroup(groupID);
	}
}
