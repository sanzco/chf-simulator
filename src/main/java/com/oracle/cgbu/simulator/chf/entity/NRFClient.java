package com.oracle.cgbu.simulator.chf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class NRFClient {

	@Id
	private String appId;
	
	@Column
	private String nrfurl;

	@Column(unique=true)
	private String appType;
	
	@Column
	private String appProfile;
	
	@Column
	private Integer healtCheck;
}
