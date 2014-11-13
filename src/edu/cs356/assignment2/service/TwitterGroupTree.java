package edu.cs356.assignment2.service;

public class TwitterGroupTree {
	private static TwitterGroupTree instance = null;	/**Holds the reference to this singleton instance*/
	private GroupComponents root;		/**Holds the root group of the tree*/
	//=========================================================
	// Constructor
	//=========================================================
	private TwitterGroupTree() {
		root = new UserGroup("Root");
	}
	
	//=========================================================
	// Methods
	//=========================================================
	/**
	 * Adds a new group to the Root user group.
	 * @param groupID	ID of new group
	 * @return	True if operation successful, false otherwise.
	 */
	public boolean addGroup(String groupID) {
		//Just call the other addGroup method with parentGroup of
		//"Root"
		return addGroup(groupID, "Root");
	}
	
	/**
	 * Add a new group with the parentGroup as the parent
	 * @param groupID	ID of new Group
	 * @param parentGroup	Parent of new group
	 * @return	True if operation successful, false otherwise
	 */
	public boolean addGroup(String groupID, String parentGroup) {
		//First check that the new Group's ID is unique. If not, return false
		if (root.checkID(groupID))
			return false;
		
		//Get the parent group and make sure it is a group and not a user.
		GroupComponents parent = root.getComponent(parentGroup);
		if (parent instanceof UserGroup)
			parent.add(new UserGroup(groupID));
		//Trying to add a group to a user. Can't do that.
		else
			return false;
		
		//AddGroup was successful
		return true;
	}
	
	/**
	 * Static method to access the singleton instance of TwitterGroupTree.
	 * @return	TwitterGroupTree instance
	 */
	public static TwitterGroupTree getInstance() {
		if (instance == null)
			instance = new TwitterGroupTree();
		return instance;
	}
	
	public boolean removeGroup(String groupID) {
		//First check if the group exists and that it is not the root group.
		if (groupID.equalsIgnoreCase("root") || !(root.checkID(groupID)))
			return false;
		
		//Get the group and make sure it's not a user. If it's a user then return false.
		GroupComponents group = root.getComponent(groupID);
		if (group instanceof UserGroup) {
			//Since only UserGroups can have children we're assuming it is a UserGroup.
			GroupComponents parent = group.getParent();
			//Add all of this group's children to the parent group
			for (int i = 0; i < group.getNumChild(); i++) {
				parent.add(group.getChild(i));
			}
			//Remove group from parent. This child is disowned.
			parent.remove(group);
		}
		else
			return false;
		
		//Successful removal of group
		return true;
	}
}
