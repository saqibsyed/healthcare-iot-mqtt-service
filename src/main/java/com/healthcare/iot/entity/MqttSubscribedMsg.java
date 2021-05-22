package com.healthcare.iot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "MQTT_SUB_MESSAGE")
@JsonPropertyOrder({ "Id", "deviceId", "deviceUser", "deviceStatus", "timeStamp" })
public class MqttSubscribedMsg {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String Id;

	@Column(name = "DEVICE_ID")
	private String deviceId;

	@Column(name = "DEVICE_USER")
	private String deviceUser;

	@Column(name = "DEVICE_STATUS")
	private String deviceStatus;

	@Column(name = "TIME_STAMP")
	private String timeStamp;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceUser() {
		return deviceUser;
	}

	public void setDeviceUser(String deviceUser) {
		this.deviceUser = deviceUser;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "MqttSubscribedMsg [Id=" + Id + ", deviceId=" + deviceId + ", deviceUser=" + deviceUser
				+ ", deviceStatus=" + deviceStatus + ", timeStamp=" + timeStamp + "]";
	}

}
