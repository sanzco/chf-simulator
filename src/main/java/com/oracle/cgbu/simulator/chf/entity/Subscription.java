package com.oracle.cgbu.simulator.chf.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Subscription {
	
	  @Id
	  private String subscriptionId;
	  
      @Column
	  private String supi;

      @Column
	  private String gpsi;

      @Column
	  private String notifUri;

      @Column
      @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	  private DateTime expiry;

      @Column
	  private String supportedFeatures;

      @PrePersist
      private void ensureId(){
          this.setSubscriptionId(UUID.randomUUID().toString());
      }
}
