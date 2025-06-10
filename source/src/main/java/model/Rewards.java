package model;

import java.io.Serializable;
import java.sql.Date;

public class Rewards implements Serializable {
	private int user_id;
	private Date gacha_time;
	private int gacha_id;

	public Rewards() {
		this(0, Date.valueOf(java.time.LocalDate.now()), 0);
	}

	public Rewards(int user_id, Date gacha_time, int gacha_id) {
		this.user_id = user_id;
		this.gacha_time = gacha_time;
		this.gacha_id = gacha_id;
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

	public int getGacha_id() {
		return gacha_id;
	}

	public void setGacha_id(int gacha_id) {
		this.gacha_id = gacha_id;
	}
}