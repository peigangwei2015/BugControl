package com.pgw.wiretap.control.domain;

public class Location {
	private int id;
	private double longitude;
	private double latitude;
	private long date;
	private String owner;
	private float direction;
	private float radius;
	private String networkLocationType;
	public Location() {
		super();
	}
	public int getId() {
		return id;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public float getDirection() {
		return direction;
	}
	public void setDirection(float direction) {
		this.direction = direction;
	}
	public String getNetworkLocationType() {
		return networkLocationType;
	}
	public void setNetworkLocationType(String networkLocationType) {
		this.networkLocationType = networkLocationType;
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", date=" + date + ", owner="
				+ owner + ", direction=" + direction + ", radius=" + radius
				+ ", networkLocationType=" + networkLocationType + "]";
	}
	
}
