package com.oracle.cgbu.simulator.chf.api.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * SubscriptionTerminationInfo
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-02T15:17:00.823608400-05:00[America/Bogota]")
public class SubscriptionTerminationInfo   {
  @JsonProperty("supi")
  private String supi;

  @JsonProperty("termCause")
  private TerminationCause termCause = null;

  public SubscriptionTerminationInfo supi(String supi) {
    this.supi = supi;
    return this;
  }

  /**
   * String identifying a Supi that shall contain either an IMSI, a network specific identifier, a Global Cable Identifier (GCI) or a Global Line Identifier (GLI) as specified in clause  2.2A of 3GPP TS 23.003. It shall be formatted as follows  - for an IMSI \"imsi-<imsi>\", where <imsi> shall be formatted according to clause 2.2    of 3GPP TS 23.003 that describes an IMSI.  - for a network specific identifier \"nai-<nai>, where <nai> shall be formatted    according to clause 28.7.2 of 3GPP TS 23.003 that describes an NAI.  - for a GCI \"gci-<gci>\", where <gci> shall be formatted according to clause 28.15.2    of 3GPP TS 23.003.  - for a GLI \"gli-<gli>\", where <gli> shall be formatted according to clause 28.16.2 of    3GPP TS 23.003.To enable that the value is used as part of an URI, the string shall    only contain characters allowed according to the \"lower-with-hyphen\" naming convention    defined in 3GPP TS 29.501. 
   * @return supi
  */
  @ApiModelProperty(required = true, value = "String identifying a Supi that shall contain either an IMSI, a network specific identifier, a Global Cable Identifier (GCI) or a Global Line Identifier (GLI) as specified in clause  2.2A of 3GPP TS 23.003. It shall be formatted as follows  - for an IMSI \"imsi-<imsi>\", where <imsi> shall be formatted according to clause 2.2    of 3GPP TS 23.003 that describes an IMSI.  - for a network specific identifier \"nai-<nai>, where <nai> shall be formatted    according to clause 28.7.2 of 3GPP TS 23.003 that describes an NAI.  - for a GCI \"gci-<gci>\", where <gci> shall be formatted according to clause 28.15.2    of 3GPP TS 23.003.  - for a GLI \"gli-<gli>\", where <gli> shall be formatted according to clause 28.16.2 of    3GPP TS 23.003.To enable that the value is used as part of an URI, the string shall    only contain characters allowed according to the \"lower-with-hyphen\" naming convention    defined in 3GPP TS 29.501. ")
  @NotNull

@Pattern(regexp="^(imsi-[0-9]{5,15}|nai-.+|gci-.+|gli-.+|.+)$") 
  public String getSupi() {
    return supi;
  }

  public void setSupi(String supi) {
    this.supi = supi;
  }

  public SubscriptionTerminationInfo termCause(TerminationCause termCause) {
    this.termCause = termCause;
    return this;
  }

  /**
   * Get termCause
   * @return termCause
  */
  @ApiModelProperty(value = "")

  @Valid

  public TerminationCause getTermCause() {
    return termCause;
  }

  public void setTermCause(TerminationCause termCause) {
    this.termCause = termCause;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionTerminationInfo subscriptionTerminationInfo = (SubscriptionTerminationInfo) o;
    return Objects.equals(this.supi, subscriptionTerminationInfo.supi) &&
        Objects.equals(this.termCause, subscriptionTerminationInfo.termCause);
  }

  @Override
  public int hashCode() {
    return Objects.hash(supi, termCause);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionTerminationInfo {\n");
    
    sb.append("    supi: ").append(toIndentedString(supi)).append("\n");
    sb.append("    termCause: ").append(toIndentedString(termCause)).append("\n");
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

