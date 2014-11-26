package edu.cs356.assignment2.gui.Visitor;

import java.util.Date;

import edu.cs356.assignment2.service.TwitterGroupTree.GroupComponentsUser;
import edu.cs356.assignment2.service.TwitterGroupTree.UserGroup;
import edu.cs356.assignment2.service.TwitterUser.User;

public class LastUserUpdateVisitor implements Visitor {
	private User lastUpdatedUser = null;
	
	//=========================================================
	// Methods
	//=========================================================
	public String getLastUpdatedID() {
		if (lastUpdatedUser == null)
			return "";
		return lastUpdatedUser.getID();
	}
	
	/**
	 * It's a leaf, so compare the date with the last updated user.
	 */
	@Override
	public void visitGroupComponentsUser(GroupComponentsUser leaf) {
		User user = leaf.getUser(leaf.getID());
		//If lastUpdatedUser is null, then make this leaf the last updated
		//user
		if (lastUpdatedUser == null)
			lastUpdatedUser = user;
		//Compared the lastUpadtedUser to this leaf user
		else {
			Date leafTime = user.getLastUpdate(); 
			//If the leaf time is after the lastUpdateUser time, then change lastUpdate to this user
			if (lastUpdatedUser.getLastUpdate().compareTo(leafTime) < 0)
				lastUpdatedUser = user;
		}
	}
	
	/**
	 * Go through all children of the group.
	 */
	@Override
	public void visitUserGroup(UserGroup group) {
		for (int i = 0; i < group.getNumChild(); i++)
			group.getChild(i).accept(this);
	}

}
