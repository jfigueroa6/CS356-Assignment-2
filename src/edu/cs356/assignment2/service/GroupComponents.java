package edu.cs356.assignment2.service;

public interface GroupComponents {
	/**
	 * Adds a GroupComponent to this object.
	 * @param grComp	Component to add
	 */
	public void add(GroupComponents grComp);
	/**
	 * Removes a GroupComponent from this object.
	 * @param grComp	Object to remove
	 */
	public void remove(GroupComponents grComp);
	/**
	 * Gets a child GroupComponent dictated by child.
	 * @param child	Component to retrieve.
	 * @return	Child component
	 */
	public GroupComponents getChild(int child);
	/**
	 * Get number of children that object has.
	 * @return	Number of children
	 */
	public int getNumChild();
	/**
	 * Compares the component's ID to the given id, and returns 
	 * if the given id exists or not.
	 * @param id	ID to check for.
	 * @return		True if the ID exists. False otherwise.
	 */
	public boolean checkID(String id);
}
