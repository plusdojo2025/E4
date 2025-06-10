package model;

import java.io.Serializable;

public class MoodRecord implements Serializable {
	private int user_id;
	private int record_id;
	private int mood;
	private String comment;
	public MoodRecord(int user_id, int record_id, int mood, String comment) {
		this.user_id = user_id;
		this.record_id = record_id;
		this.mood = mood;
		this.comment = comment;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	public int getMood() {
		return mood;
	}
	public void setMood(int mood) {
		this.mood = mood;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}



