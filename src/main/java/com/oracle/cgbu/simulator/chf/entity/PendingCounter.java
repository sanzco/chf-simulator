package com.oracle.cgbu.simulator.chf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class PendingCounter {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer counterId; 
	
	@Column
	private String counterStatus;
	
	@Column
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private DateTime activationTime;
}
