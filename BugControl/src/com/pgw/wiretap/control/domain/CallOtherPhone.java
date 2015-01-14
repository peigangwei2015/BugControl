package com.pgw.wiretap.control.domain;

public class CallOtherPhone {
	private String phoneNum;

	public CallOtherPhone(String phoneNum) {
		super();
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "CallOtherPhone [phoneNum=" + phoneNum + "]";
	}
}
