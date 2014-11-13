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
	/**
	 * Add group to tree.
	 * @param groupID	ID of new group.
	 * @return	True if operation was successful, false otherwise.
	 */
	public boolean addGroup(String groupID) {
		return treeSingleton.addGroup(groupID);
	}
	
	/**
	 * Add group to the tree under another group.
	 * @param groupID	ID of new group.
	 * @param parentID	ID of parent group
	 * @return	True if operation was successful, false otherwise.
	 */
	public boolean addGroup(String groupID, String parentID) {
		return treeSingleton.addGroup(groupID, parentID);
	}
	
	/**
	 * Add a user to the tree.
	 * @param userID	ID of new user.
	 * @return	True if operation is successful, false otherwise.
	 */
	public boolean addUser(String userID) {
		return treeSingleton.addUser(userID);
	}
	
	/**
	 * Add a user to the given group.
	 * @param userID	ID of new user.
	 * @param groupID	ID of group that user will belong to.
	 * @return	True if operation is successful, false otherwise.
	 */
	public boolean addUser(String userID, String groupID) {
		return treeSingleton.addUser(userID, groupID);
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
	
	/**
	 * Remove a group from the tree
	 * @param groupID	ID of group to remove
	 * @return	True if removal successful, false otherwise.
	 */
	public boolean removeGroup(String groupID) {
		return treeSingleton.removeGroup(groupID);
	}
	
	/**
	 * Remove a user.
	 * @param userID	ID of user to delete.
	 * @return	True if operation successful, false otherwise.
	 */
	public boolean removeUser(String userID) {
		return treeSingleton.removeUser(userID);
	}
}
