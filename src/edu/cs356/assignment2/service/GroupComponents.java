package edu.cs356.assignment2.service;

public interface GroupComponents {
	public void add(GroupComponents grComp);
	public void remove(GroupComponents grComp);
	public GroupComponents getChild(int child);
	public int getNumChild();
}
