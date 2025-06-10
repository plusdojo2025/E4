package model;

import java.io.Serializable;

public class User implements Serializable {

	private String password;
	private String email;
	
	
	//引数があるコンストラクタ
	public User(String password, String email) {
		this.password = password;
		this.email = email;
	}
	//引数がないコンストラクタ
	public User() {
		this("","");
	}
	//	パスワードのgetta 
	public String getPassword() {
		return password;
	}
	//パスワードのsetta
	public void setPassword(String password) {
		this.password = password;
	}
	//emailのgetta
	public String getEmail() {
		return email;
	}
	//emailの
	public void setEmail(String email) {
		this.email = email;
	}
	
}
