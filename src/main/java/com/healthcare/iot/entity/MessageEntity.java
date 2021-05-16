package com.healthcare.iot.entity;

public class MessageEntity {
	private Boolean docStatus;
	private Long epochTime;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(Boolean docStatus) {
		this.docStatus = docStatus;
	}

	public Long getEpochTime() {
		return epochTime;
	}

	public void setEpochTime(Long epochTime) {
		this.epochTime = epochTime;
	}

	@Override
	public String toString() {
		return "MessageEntity [docStatus=" + docStatus + ", epochTime=" + epochTime + ", msg=" + msg + "]";
	}

}
