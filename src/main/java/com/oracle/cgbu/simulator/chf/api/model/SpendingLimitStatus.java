package com.oracle.cgbu.simulator.chf.api.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * SpendingLimitStatus
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-02T15:17:00.823608400-05:00[America/Bogota]")
@JsonInclude(Include.NON_NULL)
public class SpendingLimitStatus   {
  @JsonProperty("supi")
  private String supi;

  @JsonProperty("statusInfos")
  @Valid
  private Map<String, PolicyCounterInfo> statusInfos = null;

  @JsonProperty("expiry")
  private DateTime expiry;

  @JsonProperty("supportedFeatures")
  private String supportedFeatures;

  public SpendingLimitStatus supi(String supi) {
    this.supi = supi;
    return this;
  }

  /**
   * String identifying a Supi that shall contain either an IMSI, a network specific identifier, a Global Cable Identifier (GCI) or a Global Line Identifier (GLI) as specified in clause  2.2A of 3GPP TS 23.003. It shall be formatted as follows  - for an IMSI \"imsi-<imsi>\", where <imsi> shall be formatted according to clause 2.2    of 3GPP TS 23.003 that describes an IMSI.  - for a network specific identifier \"nai-<nai>, where <nai> shall be formatted    according to clause 28.7.2 of 3GPP TS 23.003 that describes an NAI.  - for a GCI \"gci-<gci>\", where <gci> shall be formatted according to clause 28.15.2    of 3GPP TS 23.003.  - for a GLI \"gli-<gli>\", where <gli> shall be formatted according to clause 28.16.2 of    3GPP TS 23.003.To enable that the value is used as part of an URI, the string shall    only contain characters allowed according to the \"lower-with-hyphen\" naming convention    defined in 3GPP TS 29.501. 
   * @return supi
  */
  @ApiModelProperty(value = "String identifying a Supi that shall contain either an IMSI, a network specific identifier, a Global Cable Identifier (GCI) or a Global Line Identifier (GLI) as specified in clause  2.2A of 3GPP TS 23.003. It shall be formatted as follows  - for an IMSI \"imsi-<imsi>\", where <imsi> shall be formatted according to clause 2.2    of 3GPP TS 23.003 that describes an IMSI.  - for a network specific identifier \"nai-<nai>, where <nai> shall be formatted    according to clause 28.7.2 of 3GPP TS 23.003 that describes an NAI.  - for a GCI \"gci-<gci>\", where <gci> shall be formatted according to clause 28.15.2    of 3GPP TS 23.003.  - for a GLI \"gli-<gli>\", where <gli> shall be formatted according to clause 28.16.2 of    3GPP TS 23.003.To enable that the value is used as part of an URI, the string shall    only contain characters allowed according to the \"lower-with-hyphen\" naming convention    defined in 3GPP TS 29.501. ")

@Pattern(regexp="^(imsi-[0-9]{5,15}|nai-.+|gci-.+|gli-.+|.+)$") 
  public String getSupi() {
    return supi;
  }

  public void setSupi(String supi) {
    this.supi = supi;
  }

  public SpendingLimitStatus statusInfos(Map<String, PolicyCounterInfo> statusInfos) {
    this.statusInfos = statusInfos;
    return this;
  }

  public SpendingLimitStatus putStatusInfosItem(String key, PolicyCounterInfo statusInfosItem) {
    if (this.statusInfos == null) {
      this.statusInfos = new HashMap<>();
    }
    this.statusInfos.put(key, statusInfosItem);
    return this;
  }

  /**
   * Status of the requested policy counters. The key of the map is the attribute \"policyCounterId\".
   * @return statusInfos
  */
  @ApiModelProperty(value = "Status of the requested policy counters. The key of the map is the attribute \"policyCounterId\".")

  @Valid
@Size(min=1) 
  public Map<String, PolicyCounterInfo> getStatusInfos() {
    return statusInfos;
  }

  public void setStatusInfos(Map<String, PolicyCounterInfo> statusInfos) {
    this.statusInfos = statusInfos;
  }

  public SpendingLimitStatus expiry(DateTime expiry) {
    this.expiry = expiry;
    return this;
  }

  /**
   * Get expiry
   * @return expiry
  */
  @ApiModelProperty(value = "")

  @Valid

  public DateTime getExpiry() {
    return expiry;
  }

  public void setExpiry(DateTime expiry) {
    this.expiry = expiry;
  }

  public SpendingLimitStatus supportedFeatures(String supportedFeatures) {
    this.supportedFeatures = supportedFeatures;
    return this;
  }

  /**
   * A string used to indicate the features supported by an API that is used as defined in clause  6.6 in 3GPP TS 29.500. The string shall contain a bitmask indicating supported features in  hexadecimal representation Each character in the string shall take a value of \"0\" to \"9\",  \"a\" to \"f\" or \"A\" to \"F\" and shall represent the support of 4 features as described in  table 5.2.2-3. The most significant character representing the highest-numbered features shall  appear first in the string, and the character representing features 1 to 4 shall appear last  in the string. The list of features and their numbering (starting with 1) are defined  separately for each API. If the string contains a lower number of characters than there are  defined features for an API, all features that would be represented by characters that are not  present in the string are not supported. 
   * @return supportedFeatures
  */
  @ApiModelProperty(value = "A string used to indicate the features supported by an API that is used as defined in clause  6.6 in 3GPP TS 29.500. The string shall contain a bitmask indicating supported features in  hexadecimal representation Each character in the string shall take a value of \"0\" to \"9\",  \"a\" to \"f\" or \"A\" to \"F\" and shall represent the support of 4 features as described in  table 5.2.2-3. The most significant character representing the highest-numbered features shall  appear first in the string, and the character representing features 1 to 4 shall appear last  in the string. The list of features and their numbering (starting with 1) are defined  separately for each API. If the string contains a lower number of characters than there are  defined features for an API, all features that would be represented by characters that are not  present in the string are not supported. ")

@Pattern(regexp="^[A-Fa-f0-9]*$") 
  public String getSupportedFeatures() {
    return supportedFeatures;
  }

  public void setSupportedFeatures(String supportedFeatures) {
    this.supportedFeatures = supportedFeatures;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpendingLimitStatus spendingLimitStatus = (SpendingLimitStatus) o;
    return Objects.equals(this.supi, spendingLimitStatus.supi) &&
        Objects.equals(this.statusInfos, spendingLimitStatus.statusInfos) &&
        Objects.equals(this.expiry, spendingLimitStatus.expiry) &&
        Objects.equals(this.supportedFeatures, spendingLimitStatus.supportedFeatures);
  }

  @Override
  public int hashCode() {
    return Objects.hash(supi, statusInfos, expiry, supportedFeatures);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpendingLimitStatus {\n");
    
    sb.append("    supi: ").append(toIndentedString(supi)).append("\n");
    sb.append("    statusInfos: ").append(toIndentedString(statusInfos)).append("\n");
    sb.append("    expiry: ").append(toIndentedString(expiry)).append("\n");
    sb.append("    supportedFeatures: ").append(toIndentedString(supportedFeatures)).append("\n");
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

