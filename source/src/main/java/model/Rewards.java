package model;

import java.io.Serializable;
import java.sql.Date;

public class Rewards implements Serializable {
	private int user_id;
	private Date gacha_time;
	private static int gacha_type;
	private int gacha_number;

	public Rewards() {
		this.user_id = 0;
		this.gacha_number = 0;
		this.gacha_time = new Date(System.currentTimeMillis());
	}

	public Rewards(int user_id, Date gacha_time, int gacha_number) {
		this.user_id = user_id;
		this.gacha_time = gacha_time;
		this.gacha_number = gacha_number;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getGacha_time() {
		return gacha_time;
	}

	public void setGacha_time(Date gacha_time) {
		this.gacha_time = gacha_time;
	}

	public static int getGacha_type() {
		return gacha_type;
	}

	public static void setGacha_type(int gacha_type) {
		Rewards.gacha_type = gacha_type;
	}

	public int getGacha_number() {
		return gacha_number;
	}

	public void setGacha_number(int gacha_number) {
		this.gacha_number = gacha_number;
	}
}