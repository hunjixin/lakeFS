/*
 * lakeFS API
 * lakeFS HTTP API
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.lakefs.clients.api.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * AuthCapabilities
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class AuthCapabilities {
  public static final String SERIALIZED_NAME_INVITE_USER = "invite_user";
  @SerializedName(SERIALIZED_NAME_INVITE_USER)
  private Boolean inviteUser;


  public AuthCapabilities inviteUser(Boolean inviteUser) {
    
    this.inviteUser = inviteUser;
    return this;
  }

   /**
   * Get inviteUser
   * @return inviteUser
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getInviteUser() {
    return inviteUser;
  }


  public void setInviteUser(Boolean inviteUser) {
    this.inviteUser = inviteUser;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthCapabilities authCapabilities = (AuthCapabilities) o;
    return Objects.equals(this.inviteUser, authCapabilities.inviteUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inviteUser);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthCapabilities {\n");
    sb.append("    inviteUser: ").append(toIndentedString(inviteUser)).append("\n");
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

