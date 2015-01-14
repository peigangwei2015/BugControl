package com.pgw.wiretap.control.domain;

/**
 * 短信会话Bean
 */
public class ConversInfo {
	private int id;
	private long date;
	private int messageCount;
	private String snippet;
	private String address;
	private String name;
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	@Override
	public String toString() {
		return "ConversInfo [id=" + id + ", date=" + date + ", messageCount="
				+ messageCount + ", snippet=" + snippet + ", address="
				+ address + "]";
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
