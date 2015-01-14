package com.pgw.wiretap.control.domain;

public class Contacter {
	private int id;
	private String name;
	private String phoneNum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Override
	public String toString() {
		return "Contacter [id=" + id + ", name=" + name + ", phoneNum="
				+ phoneNum + "]";
	}

}
