package edu.cs356.assignment2.service;

import java.util.ArrayList;
import java.util.List;

public class UserGroup implements GroupComponents{
	private String id;
	private List<GroupComponents> children = null;
	
	public UserGroup(String id) {
		this.id = id;
		children = new ArrayList<GroupComponents>();
	}
	
	public String getID() {
		return id;
	}

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
}
