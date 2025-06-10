package model;

import java.io.Serializable;

public class Rewards implements Serializable {
	private String gacha_low;
	private String gacha_mid;
	private String gacha_high;
	
	public Rewards(String gacha_low, String gacha_mid, String gacha_high) {
		this.gacha_low = gacha_low;
		this.gacha_mid = gacha_mid;
		this.gacha_high = gacha_high;
	}

	public String getGacha_low() {
		return gacha_low;
	}

	public void setGacha_low(String gacha_low) {
		this.gacha_low = gacha_low;
	}

	public String getGacha_mid() {
		return gacha_mid;
	}

	public void setGacha_mid(String gacha_mid) {
		this.gacha_mid = gacha_mid;
	}

	public String getGacha_high() {
		return gacha_high;
	}

	public void setGacha_high(String gacha_high) {
		this.gacha_high = gacha_high;
	}

	}
