package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class MoodRecord implements Serializable {
    private int user_id;
    private Date record_date;
    private int mood;
    private String comment;
    private Timestamp created_at;
    private Time time; // ← 追加！

    public MoodRecord() {
        this(0, Date.valueOf(java.time.LocalDate.now()), 0, "");
    }

    public MoodRecord(int user_id, Date record_date, int mood, String comment) {
        this.user_id = user_id;
        this.record_date = record_date;
        this.mood = mood;
        this.comment = comment;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
