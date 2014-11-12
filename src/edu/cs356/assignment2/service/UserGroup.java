package edu.cs356.assignment2.service;

import java.util.ArrayList;
import java.util.List;

public class UserGroup implements GroupComponents{
	private String id;		/**Group ID*/
	private List<GroupComponents> children = null;	/**List of child GroupComponents*/
	
	//=========================================================
	// Constructor
	//=========================================================
	/**
	 * Constructor that establishes UserGroup ID and initializes children list.
	 * @param id	Group ID
	 */
	public UserGroup(String id) {
		this.id = id;
		children = new ArrayList<GroupComponents>();
	}
	
	//=========================================================
	// Methods
	//=========================================================
	@Override
	public void add(GroupComponents grComp) {
		if (grComp != null)
			children.add(grComp);		
	}

	@Override
	public void remove(GroupComponents grComp) {
		if (grComp != null)
			children.remove(grComp);
	}

	@Override
	public GroupComponents getChild(int child) {
		if (child >= 0 && child < children.size())
			return children.get(child);
		return null;
	}

	@Override
	public int getNumChild() {
		return children.size();
	}
	
	/**
	 * This class will check its ID followed by the child IDs.
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkID(String id) {
		//First check own ID
		if (this.id.equalsIgnoreCase(id))
			return true;
		
		//Now check child IDs
		for (GroupComponents child : children) {
			//If the child ID matches the given id then return true.
			if (child.checkID(id))
				return true;
		}
		
		//No match for id
		return false;
	}
}
