package com.pgw.wiretap.control.domain;

public class User {
	private int id;
	private String ip;
	private int isconn;
	private String name;
	public User() {
		super();
	}
	public User(int id, String name, String ip, int isconn) {
		super();
		this.id = id;
		this.name = name;
		this.ip = ip;
		this.isconn = isconn;
	}
	public int getId() {
		return id;
	}
	public String getIp() {
		return ip;
	}
	public int getIsconn() {
		return isconn;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setIsconn(int isconn) {
		this.isconn = isconn;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", ip=" + ip + ", isconn="
				+ isconn + "]";
	}
}
