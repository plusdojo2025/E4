package model;

import java.io.Serializable;

public class User implements Serializable {
	private String password;
	private String email;

	public User() {
		this("", "");
	}

	public User(String password, String email) {
		this.password = password;
		this.email = email;
	}

	// getta setta
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
