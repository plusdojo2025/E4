package model;

import java.io.Serializable;

public class Rewards implements Serializable {
	private int user_id;
	private String gacha_time;
	private static int gacha_type;
	private String gacha_number;
	
	public Rewards() {
		this(gacha_type, "", gacha_type, "");
	}
	
	public Rewards(int user_id, String gacha_time, int gacha_type, String gacha_number) {

		this.user_id = user_id;
		this.gacha_time = gacha_time;
		this.gacha_type = gacha_type;
		this.gacha_number = gacha_number;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getGacha_time() {
		return gacha_time;
	}
	public void setGacha_time(String gacha_time) {
		this.gacha_time = gacha_time;
	}
	public int getGacha_type() {
		return gacha_type;
	}
	public void setGacha_type(int gacha_type) {
		this.gacha_type = gacha_type;
	}
	public String getGacha_number() {
		return gacha_number;
	}
	public void setGacha_number(String gacha_number) {
		this.gacha_number = gacha_number;
	}
}