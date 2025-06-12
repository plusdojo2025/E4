package model;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String email;
	private String password;

	public User() {
		this(0, "", "");
	}

	public User(String email, String password) {
		this(0, email, password);
	}

	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
