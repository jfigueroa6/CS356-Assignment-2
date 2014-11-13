package edu.cs356.assignment2.service;

public class GroupComponentsUser implements GroupComponents {
	private User user = null;	/**User that resides in this leaf*/
	private GroupComponents parent = null;	/**Parent of leaf*/
	
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
		if (user.getID().equalsIgnoreCase(id))
			return true;
		//No match
		return false;
	}
	
	/**
	 * If the given ID matches the stored User's ID, then it 
	 * will return this object.
	 * {@inheritDoc}
	 */
	@Override
	public GroupComponents getComponent(String id) {
		//First check own ID
		if (this.user.getID().equalsIgnoreCase(id))
			return this;
		return null;
	}

	@Override
	public void setParent(GroupComponents parent) {
		this.parent = parent;
	}

	@Override
	public GroupComponents getParent() {
		return parent;
	}
}
