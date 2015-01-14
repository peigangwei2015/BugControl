package com.pgw.wiretap.control.domain;
/**
 * 短信Bean
 */
public class SmsInfo {
	private String body;
	private long date;
	private int id;
	private String address;
	private String name;
	private int type;
	public SmsInfo() {
		super();
	}
	public String getBody() {
		return body;
	}
	public int getId() {
		return id;
	}
	public int getType() {
		return type;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SmsInfo [body=" + body + ", date=" + date + ", id=" + id
				+ ", address=" + address + ", name=" + name + ", type=" + type
				+ "]";
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	
}
