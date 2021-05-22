package com.healthcare.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.iot.entity.MqttSubscribedMsg;

@Repository
public interface MessageRepo extends JpaRepository<MqttSubscribedMsg, String> {

}
