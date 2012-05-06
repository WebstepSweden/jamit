package se.diversify.jamit.dao;

public class User {
	private String name;
	private String email;
	private UserRole userRole;

	public User(String name, String email, UserRole userRole) {
		super();
		this.name = name;
		this.email = email;
		this.userRole = userRole;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public UserRole getUserRole() {
		return userRole;
	}
}
