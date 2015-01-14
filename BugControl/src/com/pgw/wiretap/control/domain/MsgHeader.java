package com.pgw.wiretap.control.domain;

public class MsgHeader {
	private String getter;
	private String sender;
	private int type;
	public MsgHeader() {
		super();
	}
	public MsgHeader(String getter, String sender, int type) {
		super();
		this.getter = getter;
		this.sender = sender;
		this.type = type;
	}
	public String getGetter() {
		return getter;
	}
	public String getSender() {
		return sender;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	@Override
	public String toString() {
		return "MsgHeader [getter=" + getter + ", sender=" + sender + ", type="
				+ type + "]";
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
