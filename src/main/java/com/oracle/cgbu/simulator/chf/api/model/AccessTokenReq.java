package com.oracle.cgbu.simulator.chf.api.model;

import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * AccessTokenReq
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-02T15:17:00.823608400-05:00[America/Bogota]")
public class AccessTokenReq   {
  /**
   * Gets or Sets grantType
   */
  public enum GrantTypeEnum {
    CLIENT_CREDENTIALS("client_credentials");

    private String value;

    GrantTypeEnum(String value) {
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
    public static GrantTypeEnum fromValue(String value) {
      for (GrantTypeEnum b : GrantTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("grant_type")
  private GrantTypeEnum grantType;

  @JsonProperty("nfInstanceId")
  private UUID nfInstanceId;

  @JsonProperty("nfType")
  private NFType nfType = null;

  @JsonProperty("targetNfType")
  private NFType targetNfType = null;

  @JsonProperty("scope")
  private String scope;

  @JsonProperty("targetNfInstanceId")
  private UUID targetNfInstanceId;

  @JsonProperty("requesterPlmn")
  private PlmnId requesterPlmn;

  @JsonProperty("targetPlmn")
  private PlmnId targetPlmn;

  public AccessTokenReq grantType(GrantTypeEnum grantType) {
    this.grantType = grantType;
    return this;
  }

  /**
   * Get grantType
   * @return grantType
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public GrantTypeEnum getGrantType() {
    return grantType;
  }

  public void setGrantType(GrantTypeEnum grantType) {
    this.grantType = grantType;
  }

  public AccessTokenReq nfInstanceId(UUID nfInstanceId) {
    this.nfInstanceId = nfInstanceId;
    return this;
  }

  /**
   * String uniquely identifying a NF instance. The format of the NF Instance ID shall be a  Universally Unique Identifier (UUID) version 4, as described in IETF RFC 4122.  
   * @return nfInstanceId
  */
  @ApiModelProperty(required = true, value = "String uniquely identifying a NF instance. The format of the NF Instance ID shall be a  Universally Unique Identifier (UUID) version 4, as described in IETF RFC 4122.  ")
  @NotNull

  @Valid

  public UUID getNfInstanceId() {
    return nfInstanceId;
  }

  public void setNfInstanceId(UUID nfInstanceId) {
    this.nfInstanceId = nfInstanceId;
  }

  public AccessTokenReq nfType(NFType nfType) {
    this.nfType = nfType;
    return this;
  }

  /**
   * Get nfType
   * @return nfType
  */
  @ApiModelProperty(value = "")

  @Valid

  public NFType getNfType() {
    return nfType;
  }

  public void setNfType(NFType nfType) {
    this.nfType = nfType;
  }

  public AccessTokenReq targetNfType(NFType targetNfType) {
    this.targetNfType = targetNfType;
    return this;
  }

  /**
   * Get targetNfType
   * @return targetNfType
  */
  @ApiModelProperty(value = "")

  @Valid

  public NFType getTargetNfType() {
    return targetNfType;
  }

  public void setTargetNfType(NFType targetNfType) {
    this.targetNfType = targetNfType;
  }

  public AccessTokenReq scope(String scope) {
    this.scope = scope;
    return this;
  }

  /**
   * Get scope
   * @return scope
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Pattern(regexp="^([a-zA-Z0-9_-]+)( [a-zA-Z0-9_-]+)*$") 
  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public AccessTokenReq targetNfInstanceId(UUID targetNfInstanceId) {
    this.targetNfInstanceId = targetNfInstanceId;
    return this;
  }

  /**
   * String uniquely identifying a NF instance. The format of the NF Instance ID shall be a  Universally Unique Identifier (UUID) version 4, as described in IETF RFC 4122.  
   * @return targetNfInstanceId
  */
  @ApiModelProperty(value = "String uniquely identifying a NF instance. The format of the NF Instance ID shall be a  Universally Unique Identifier (UUID) version 4, as described in IETF RFC 4122.  ")

  @Valid

  public UUID getTargetNfInstanceId() {
    return targetNfInstanceId;
  }

  public void setTargetNfInstanceId(UUID targetNfInstanceId) {
    this.targetNfInstanceId = targetNfInstanceId;
  }

  public AccessTokenReq requesterPlmn(PlmnId requesterPlmn) {
    this.requesterPlmn = requesterPlmn;
    return this;
  }

  /**
   * Get requesterPlmn
   * @return requesterPlmn
  */
  @ApiModelProperty(value = "")

  @Valid

  public PlmnId getRequesterPlmn() {
    return requesterPlmn;
  }

  public void setRequesterPlmn(PlmnId requesterPlmn) {
    this.requesterPlmn = requesterPlmn;
  }

  public AccessTokenReq targetPlmn(PlmnId targetPlmn) {
    this.targetPlmn = targetPlmn;
    return this;
  }

  /**
   * Get targetPlmn
   * @return targetPlmn
  */
  @ApiModelProperty(value = "")

  @Valid

  public PlmnId getTargetPlmn() {
    return targetPlmn;
  }

  public void setTargetPlmn(PlmnId targetPlmn) {
    this.targetPlmn = targetPlmn;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccessTokenReq accessTokenReq = (AccessTokenReq) o;
    return Objects.equals(this.grantType, accessTokenReq.grantType) &&
        Objects.equals(this.nfInstanceId, accessTokenReq.nfInstanceId) &&
        Objects.equals(this.nfType, accessTokenReq.nfType) &&
        Objects.equals(this.targetNfType, accessTokenReq.targetNfType) &&
        Objects.equals(this.scope, accessTokenReq.scope) &&
        Objects.equals(this.targetNfInstanceId, accessTokenReq.targetNfInstanceId) &&
        Objects.equals(this.requesterPlmn, accessTokenReq.requesterPlmn) &&
        Objects.equals(this.targetPlmn, accessTokenReq.targetPlmn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(grantType, nfInstanceId, nfType, targetNfType, scope, targetNfInstanceId, requesterPlmn, targetPlmn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccessTokenReq {\n");
    
    sb.append("    grantType: ").append(toIndentedString(grantType)).append("\n");
    sb.append("    nfInstanceId: ").append(toIndentedString(nfInstanceId)).append("\n");
    sb.append("    nfType: ").append(toIndentedString(nfType)).append("\n");
    sb.append("    targetNfType: ").append(toIndentedString(targetNfType)).append("\n");
    sb.append("    scope: ").append(toIndentedString(scope)).append("\n");
    sb.append("    targetNfInstanceId: ").append(toIndentedString(targetNfInstanceId)).append("\n");
    sb.append("    requesterPlmn: ").append(toIndentedString(requesterPlmn)).append("\n");
    sb.append("    targetPlmn: ").append(toIndentedString(targetPlmn)).append("\n");
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

