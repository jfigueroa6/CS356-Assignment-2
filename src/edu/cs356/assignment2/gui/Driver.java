package edu.cs356.assignment2.gui;

import edu.cs356.assignment2.service.GroupComponents;
import edu.cs356.assignment2.service.GroupComponentsUser;
import edu.cs356.assignment2.service.User;
import edu.cs356.assignment2.service.UserGroup;

public class Driver {

	public static void main(String[] args) {
		User a = new User("22");
		User b = new User("23");
		User c = new User("24");
		GroupComponents grp = new UserGroup("68"),
						grp2 = new UserGroup("70");
		grp.add(new GroupComponentsUser(a));
		grp2.add(new GroupComponentsUser(b));
		grp2.add(new GroupComponentsUser(c));
		grp.add(grp2);
		
		System.out.println(grp.checkID("25"));
		System.out.println(grp.checkID("68"));
	}

}
