package model;

import java.io.Serializable;
import java.sql.Date;

public class Rewards implements Serializable {
	private int user_id;
	private Date gacha_time;
	private String gacha_item;

	public Rewards() {
		this(0, Date.valueOf(java.time.LocalDate.now()), "");
	}

	public Rewards(int user_id, Date gacha_time, String gacha_item) {
		this.user_id = user_id;
		this.gacha_time = gacha_time;
		this.gacha_item = gacha_item;
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

	public String getGacha_item() {
		return gacha_item;
	}

	public void setGacha_item(String gacha_item) {
		this.gacha_item = gacha_item;
	}
}