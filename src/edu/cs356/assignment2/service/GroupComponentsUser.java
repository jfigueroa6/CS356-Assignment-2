package edu.cs356.assignment2.service;

public class GroupComponentsUser implements GroupComponents {
	private User user = null;	/**User that resides in this leaf*/
	
	//=========================================================
	// Constructor
	//=========================================================
	/**
	 * Initializes user to the passed user object
	 * @param user	User that composes this leaf
	 */
	public GroupComponentsUser(User user) {
		this.user = user;
	}
	
	//=========================================================
	// Methods
	//=========================================================
	/**
	 * This class cannot add child components.
	 * {@inheritDoc}
	 */
	@Override
	public void add(GroupComponents grComp) {
		//NOP
	}
	
	/**
	 * This class cannot remove children.
	 * {@inheritDoc}
	 */
	@Override
	public void remove(GroupComponents grComp) {
		//NOP
	}
	
	/**
	 * Since this class cannot have child components, it will
	 * return null.
	 * {@inheritDoc}
	 */
	@Override
	public GroupComponents getChild(int child) {
		return null;
	}
	
	/**
	 * Since this is a leaf component, it cannot have child components.
	 * It will always return 0.
	 * {@inheritDoc}
	 */
	@Override
	public int getNumChild() {
		return 0;
	}
	
	@Override
	public boolean checkID(String id) {
		//Check ID with given id
		if (user.getID().equals(id))
			return true;
		//No match
		return false;
	}
}
