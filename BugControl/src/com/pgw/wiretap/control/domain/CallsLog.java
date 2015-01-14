package com.pgw.wiretap.control.domain;

public class CallsLog {
	private String date;
	private int id;
	private int is_read;
	private String name;
	private String number;
	private int type;
	private int duration;
	public CallsLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CallsLog(String date, int id, int is_read, String name,
			String number, int type, int duration) {
		super();
		this.date = date;
		this.id = id;
		this.is_read = is_read;
		this.name = name;
		this.number = number;
		this.type = type;
		this.duration = duration;
	}
	public String getDate() {
		return date;
	}
	public int getId() {
		return id;
	}
	public int getIs_read() {
		return is_read;
	}
	public String getName() {
		return name;
	}
	public String getNumber() {
		return number;
	}
	public int getType() {
		return type;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIs_read(int is_read) {
		this.is_read = is_read;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "CallsLog [date=" + date + ", id=" + id + ", is_read=" + is_read
				+ ", name=" + name + ", number=" + number + ", type=" + type
				+ ", duration=" + duration + "]";
	}

}
