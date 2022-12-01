package com.oracle.cgbu.simulator.chf.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Counter {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer counterId;
	
	@Column
	private String name;
	
	@Column
	private String currentStatus;
	
	@OneToMany(cascade = CascadeType.ALL)
    private List<PendingCounter> pendingCounters;
	
	
	
}
