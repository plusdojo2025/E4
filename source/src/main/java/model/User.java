package model;

import java.io.Serializable;

public class User implements Serializable {
	private String email;
	private String password;

	// 引数がないコンストラクタ
	public User() {
		this("", "");
	}

	// 引数があるコンストラクタ
	public User(String email, String password) {
		this.password = password;
		this.email = email;
	}

	// emailのgetta
	public String getEmail() {
		return email;
	}

	// emailの
	public void setEmail(String email) {
		this.email = email;
	}

	// パスワードのgetta
	public String getPassword() {
		return password;
	}

	// パスワードのsetta
	public void setPassword(String password) {
		this.password = password;
	}
}
