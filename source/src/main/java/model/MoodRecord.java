package model;

import java.io.Serializable;

public class MoodRecord implements Serializable {
	private String comment;

	public MoodRecord(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
