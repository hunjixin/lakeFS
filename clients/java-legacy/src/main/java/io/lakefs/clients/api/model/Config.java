/*
 * lakeFS API
 * lakeFS HTTP API
 *
 * The version of the OpenAPI document: 1.0.0
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
import io.lakefs.clients.api.model.StorageConfig;
import io.lakefs.clients.api.model.VersionConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Config
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class Config {
  public static final String SERIALIZED_NAME_VERSION_CONFIG = "version_config";
  @SerializedName(SERIALIZED_NAME_VERSION_CONFIG)
  private VersionConfig versionConfig;

  public static final String SERIALIZED_NAME_STORAGE_CONFIG = "storage_config";
  @SerializedName(SERIALIZED_NAME_STORAGE_CONFIG)
  private StorageConfig storageConfig;


  public Config versionConfig(VersionConfig versionConfig) {
    
    this.versionConfig = versionConfig;
    return this;
  }

   /**
   * Get versionConfig
   * @return versionConfig
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public VersionConfig getVersionConfig() {
    return versionConfig;
  }


  public void setVersionConfig(VersionConfig versionConfig) {
    this.versionConfig = versionConfig;
  }


  public Config storageConfig(StorageConfig storageConfig) {
    
    this.storageConfig = storageConfig;
    return this;
  }

   /**
   * Get storageConfig
   * @return storageConfig
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StorageConfig getStorageConfig() {
    return storageConfig;
  }


  public void setStorageConfig(StorageConfig storageConfig) {
    this.storageConfig = storageConfig;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Config config = (Config) o;
    return Objects.equals(this.versionConfig, config.versionConfig) &&
        Objects.equals(this.storageConfig, config.storageConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(versionConfig, storageConfig);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Config {\n");
    sb.append("    versionConfig: ").append(toIndentedString(versionConfig)).append("\n");
    sb.append("    storageConfig: ").append(toIndentedString(storageConfig)).append("\n");
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

