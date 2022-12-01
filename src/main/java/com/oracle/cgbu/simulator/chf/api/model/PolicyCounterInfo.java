package com.oracle.cgbu.simulator.chf.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * PolicyCounterInfo
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-02T15:17:00.823608400-05:00[America/Bogota]")
public class PolicyCounterInfo   {
  @JsonProperty("policyCounterId")
  private String policyCounterId;

  @JsonProperty("currentStatus")
  private String currentStatus;

  @JsonProperty("penPolCounterStatuses")
  @Valid
  private List<PendingPolicyCounterStatus> penPolCounterStatuses = null;

  public PolicyCounterInfo policyCounterId(String policyCounterId) {
    this.policyCounterId = policyCounterId;
    return this;
  }

  /**
   * Identifies a policy counter.
   * @return policyCounterId
  */
  @ApiModelProperty(required = true, value = "Identifies a policy counter.")
  @NotNull


  public String getPolicyCounterId() {
    return policyCounterId;
  }

  public void setPolicyCounterId(String policyCounterId) {
    this.policyCounterId = policyCounterId;
  }

  public PolicyCounterInfo currentStatus(String currentStatus) {
    this.currentStatus = currentStatus;
    return this;
  }

  /**
   * Identifies the policy counter status applicable for a specific policy counter identified by the policyCounterId. The values (e.g. valid, invalid or any other status) are not specified. The interpretation and actions related to the defined values are out of scope of 3GPP.
   * @return currentStatus
  */
  @ApiModelProperty(required = true, value = "Identifies the policy counter status applicable for a specific policy counter identified by the policyCounterId. The values (e.g. valid, invalid or any other status) are not specified. The interpretation and actions related to the defined values are out of scope of 3GPP.")
  @NotNull


  public String getCurrentStatus() {
    return currentStatus;
  }

  public void setCurrentStatus(String currentStatus) {
    this.currentStatus = currentStatus;
  }

  public PolicyCounterInfo penPolCounterStatuses(List<PendingPolicyCounterStatus> penPolCounterStatuses) {
    this.penPolCounterStatuses = penPolCounterStatuses;
    return this;
  }

  public PolicyCounterInfo addPenPolCounterStatusesItem(PendingPolicyCounterStatus penPolCounterStatusesItem) {
    if (this.penPolCounterStatuses == null) {
      this.penPolCounterStatuses = new ArrayList<>();
    }
    this.penPolCounterStatuses.add(penPolCounterStatusesItem);
    return this;
  }

  /**
   * Provides the pending policy counter status.
   * @return penPolCounterStatuses
  */
  @ApiModelProperty(value = "Provides the pending policy counter status.")

  @Valid
@Size(min=1) 
  public List<PendingPolicyCounterStatus> getPenPolCounterStatuses() {
    return penPolCounterStatuses;
  }

  public void setPenPolCounterStatuses(List<PendingPolicyCounterStatus> penPolCounterStatuses) {
    this.penPolCounterStatuses = penPolCounterStatuses;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PolicyCounterInfo policyCounterInfo = (PolicyCounterInfo) o;
    return Objects.equals(this.policyCounterId, policyCounterInfo.policyCounterId) &&
        Objects.equals(this.currentStatus, policyCounterInfo.currentStatus) &&
        Objects.equals(this.penPolCounterStatuses, policyCounterInfo.penPolCounterStatuses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(policyCounterId, currentStatus, penPolCounterStatuses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PolicyCounterInfo {\n");
    
    sb.append("    policyCounterId: ").append(toIndentedString(policyCounterId)).append("\n");
    sb.append("    currentStatus: ").append(toIndentedString(currentStatus)).append("\n");
    sb.append("    penPolCounterStatuses: ").append(toIndentedString(penPolCounterStatuses)).append("\n");
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

