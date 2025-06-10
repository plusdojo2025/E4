package model;

import java.io.Serializable;
import java.sql.Date;

public class Rewards implements Serializable {
	private int user_id;
	private Date gacha_time;
	private String gacha_type;
	private int gacha_number;

	public Rewards() {
		this(0, Date.valueOf(java.time.LocalDate.now()), "", 0);
	}

	public Rewards(int user_id, Date gacha_time, String gacha_type, int gacha_number) {
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

	public Date getGacha_time() {
		return gacha_time;
	}

	public void setGacha_time(Date gacha_time) {
		this.gacha_time = gacha_time;
	}

	public String getGacha_type() {
		return gacha_type;
	}

	public void setGacha_type(String gacha_type) {
		this.gacha_type = gacha_type;
	}

	public int getGacha_number() {
		return gacha_number;
	}

	public void setGacha_number(int gacha_number) {
		this.gacha_number = gacha_number;
	}
}