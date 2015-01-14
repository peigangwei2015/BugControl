package com.pgw.wiretap.control.domain;

public class SendSmsToOther {
	private String senderNum;
	private String body;

	public SendSmsToOther(String senderNum, String body) {
		this.senderNum=senderNum;
		this.body=body;
	}

	public String getSenderNum() {
		return senderNum;
	}

	public void setSenderNum(String senderNum) {
		this.senderNum = senderNum;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "SendSmsToOther [senderNum=" + senderNum + ", body=" + body
				+ "]";
	}

}
