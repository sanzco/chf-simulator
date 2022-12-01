package com.oracle.cgbu.simulator.chf.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets NFType_anyOf
 */
public enum NFTypeAnyOf {
  
  NRF("NRF"),
  
  UDM("UDM"),
  
  AMF("AMF"),
  
  SMF("SMF"),
  
  AUSF("AUSF"),
  
  NEF("NEF"),
  
  PCF("PCF"),
  
  SMSF("SMSF"),
  
  NSSF("NSSF"),
  
  UDR("UDR"),
  
  LMF("LMF"),
  
  GMLC("GMLC"),
  
  _5G_EIR("5G_EIR"),
  
  SEPP("SEPP"),
  
  UPF("UPF"),
  
  N3IWF("N3IWF"),
  
  AF("AF"),
  
  UDSF("UDSF"),
  
  BSF("BSF"),
  
  CHF("CHF"),
  
  NWDAF("NWDAF");

  private String value;

  NFTypeAnyOf(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static NFTypeAnyOf fromValue(String value) {
    for (NFTypeAnyOf b : NFTypeAnyOf.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

