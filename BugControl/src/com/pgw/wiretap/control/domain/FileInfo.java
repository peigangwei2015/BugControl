package com.pgw.wiretap.control.domain;

public class FileInfo {
	private long date;
	private String name;
	private String path;
	private long size;
	public FileInfo() {
		super();
	}
	public FileInfo(long date, String name, String path, long size) {
		super();
		this.date = date;
		this.name = name;
		this.path = path;
		this.size = size;
	}
	public long getDate() {
		return date;
	}
	public String getName() {
		return name;
	}
	public String getPath() {
		return path;
	}
	public long getSize() {
		return size;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setSize(long size) {
		this.size = size;
	}
}
