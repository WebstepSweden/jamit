package se.diversify.jamit.dao;

import com.vaadin.data.util.sqlcontainer.SQLContainer;

public class UserDao {

	private SQLContainer sqlContainer = DB.getSQLContainer("users");
	
	public void addUser(User user) {
		sqlContainer.addItem(user);
	}
}
