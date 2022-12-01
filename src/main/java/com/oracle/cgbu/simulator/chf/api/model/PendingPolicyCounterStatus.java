package com.oracle.cgbu.simulator.chf.api.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * PendingPolicyCounterStatus
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-02T15:17:00.823608400-05:00[America/Bogota]")
public class PendingPolicyCounterStatus   {
  @JsonProperty("policyCounterStatus")
  private String policyCounterStatus;

  @JsonProperty("activationTime")
  private DateTime activationTime;

  public PendingPolicyCounterStatus policyCounterStatus(String policyCounterStatus) {
    this.policyCounterStatus = policyCounterStatus;
    return this;
  }

  /**
   * Identifies the policy counter status applicable for a specific policy counter identified by the policyCounterId. The values (e.g. valid, invalid or any other status) are not specified. The interpretation and actions related to the defined values are out of scope of 3GPP.
   * @return policyCounterStatus
  */
  @ApiModelProperty(required = true, value = "Identifies the policy counter status applicable for a specific policy counter identified by the policyCounterId. The values (e.g. valid, invalid or any other status) are not specified. The interpretation and actions related to the defined values are out of scope of 3GPP.")
  @NotNull


  public String getPolicyCounterStatus() {
    return policyCounterStatus;
  }

  public void setPolicyCounterStatus(String policyCounterStatus) {
    this.policyCounterStatus = policyCounterStatus;
  }

  public PendingPolicyCounterStatus activationTime(DateTime activationTime) {
    this.activationTime = activationTime;
    return this;
  }

  /**
   * Get activationTime
   * @return activationTime
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public DateTime getActivationTime() {
    return activationTime;
  }

  public void setActivationTime(DateTime activationTime) {
    this.activationTime = activationTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PendingPolicyCounterStatus pendingPolicyCounterStatus = (PendingPolicyCounterStatus) o;
    return Objects.equals(this.policyCounterStatus, pendingPolicyCounterStatus.policyCounterStatus) &&
        Objects.equals(this.activationTime, pendingPolicyCounterStatus.activationTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(policyCounterStatus, activationTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PendingPolicyCounterStatus {\n");
    
    sb.append("    policyCounterStatus: ").append(toIndentedString(policyCounterStatus)).append("\n");
    sb.append("    activationTime: ").append(toIndentedString(activationTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

