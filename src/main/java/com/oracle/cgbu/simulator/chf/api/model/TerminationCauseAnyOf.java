package com.oracle.cgbu.simulator.chf.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets TerminationCause_anyOf
 */
public enum TerminationCauseAnyOf {
  
  REMOVED_SUBSCRIBER("REMOVED_SUBSCRIBER");

  private String value;

  TerminationCauseAnyOf(String value) {
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
  public static TerminationCauseAnyOf fromValue(String value) {
    for (TerminationCauseAnyOf b : TerminationCauseAnyOf.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

