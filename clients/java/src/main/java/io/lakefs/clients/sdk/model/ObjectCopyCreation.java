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


package io.lakefs.clients.sdk.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import io.lakefs.clients.sdk.JSON;

/**
 * ObjectCopyCreation
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ObjectCopyCreation {
  public static final String SERIALIZED_NAME_SRC_PATH = "src_path";
  @SerializedName(SERIALIZED_NAME_SRC_PATH)
  private String srcPath;

  public static final String SERIALIZED_NAME_SRC_REF = "src_ref";
  @SerializedName(SERIALIZED_NAME_SRC_REF)
  private String srcRef;

  public ObjectCopyCreation() {
  }

  public ObjectCopyCreation srcPath(String srcPath) {
    
    this.srcPath = srcPath;
    return this;
  }

   /**
   * path of the copied object relative to the ref
   * @return srcPath
  **/
  @javax.annotation.Nonnull
  public String getSrcPath() {
    return srcPath;
  }


  public void setSrcPath(String srcPath) {
    this.srcPath = srcPath;
  }


  public ObjectCopyCreation srcRef(String srcRef) {
    
    this.srcRef = srcRef;
    return this;
  }

   /**
   * a reference, if empty uses the provided branch as ref
   * @return srcRef
  **/
  @javax.annotation.Nullable
  public String getSrcRef() {
    return srcRef;
  }


  public void setSrcRef(String srcRef) {
    this.srcRef = srcRef;
  }

  /**
   * A container for additional, undeclared properties.
   * This is a holder for any undeclared properties as specified with
   * the 'additionalProperties' keyword in the OAS document.
   */
  private Map<String, Object> additionalProperties;

  /**
   * Set the additional (undeclared) property with the specified name and value.
   * If the property does not already exist, create it otherwise replace it.
   *
   * @param key name of the property
   * @param value value of the property
   * @return the ObjectCopyCreation instance itself
   */
  public ObjectCopyCreation putAdditionalProperty(String key, Object value) {
    if (this.additionalProperties == null) {
        this.additionalProperties = new HashMap<String, Object>();
    }
    this.additionalProperties.put(key, value);
    return this;
  }

  /**
   * Return the additional (undeclared) property.
   *
   * @return a map of objects
   */
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  /**
   * Return the additional (undeclared) property with the specified name.
   *
   * @param key name of the property
   * @return an object
   */
  public Object getAdditionalProperty(String key) {
    if (this.additionalProperties == null) {
        return null;
    }
    return this.additionalProperties.get(key);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjectCopyCreation objectCopyCreation = (ObjectCopyCreation) o;
    return Objects.equals(this.srcPath, objectCopyCreation.srcPath) &&
        Objects.equals(this.srcRef, objectCopyCreation.srcRef)&&
        Objects.equals(this.additionalProperties, objectCopyCreation.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(srcPath, srcRef, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectCopyCreation {\n");
    sb.append("    srcPath: ").append(toIndentedString(srcPath)).append("\n");
    sb.append("    srcRef: ").append(toIndentedString(srcRef)).append("\n");
    sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
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


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("src_path");
    openapiFields.add("src_ref");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("src_path");
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ObjectCopyCreation
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ObjectCopyCreation.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ObjectCopyCreation is not found in the empty JSON string", ObjectCopyCreation.openapiRequiredFields.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ObjectCopyCreation.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("src_path").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `src_path` to be a primitive type in the JSON string but got `%s`", jsonObj.get("src_path").toString()));
      }
      if ((jsonObj.get("src_ref") != null && !jsonObj.get("src_ref").isJsonNull()) && !jsonObj.get("src_ref").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `src_ref` to be a primitive type in the JSON string but got `%s`", jsonObj.get("src_ref").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ObjectCopyCreation.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ObjectCopyCreation' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ObjectCopyCreation> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ObjectCopyCreation.class));

       return (TypeAdapter<T>) new TypeAdapter<ObjectCopyCreation>() {
           @Override
           public void write(JsonWriter out, ObjectCopyCreation value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             obj.remove("additionalProperties");
             // serialize additional properties
             if (value.getAdditionalProperties() != null) {
               for (Map.Entry<String, Object> entry : value.getAdditionalProperties().entrySet()) {
                 if (entry.getValue() instanceof String)
                   obj.addProperty(entry.getKey(), (String) entry.getValue());
                 else if (entry.getValue() instanceof Number)
                   obj.addProperty(entry.getKey(), (Number) entry.getValue());
                 else if (entry.getValue() instanceof Boolean)
                   obj.addProperty(entry.getKey(), (Boolean) entry.getValue());
                 else if (entry.getValue() instanceof Character)
                   obj.addProperty(entry.getKey(), (Character) entry.getValue());
                 else {
                   obj.add(entry.getKey(), gson.toJsonTree(entry.getValue()).getAsJsonObject());
                 }
               }
             }
             elementAdapter.write(out, obj);
           }

           @Override
           public ObjectCopyCreation read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             ObjectCopyCreation instance = thisAdapter.fromJsonTree(jsonObj);
             for (Map.Entry<String, JsonElement> entry : jsonObj.entrySet()) {
               if (!openapiFields.contains(entry.getKey())) {
                 if (entry.getValue().isJsonPrimitive()) { // primitive type
                   if (entry.getValue().getAsJsonPrimitive().isString())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsString());
                   else if (entry.getValue().getAsJsonPrimitive().isNumber())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsNumber());
                   else if (entry.getValue().getAsJsonPrimitive().isBoolean())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsBoolean());
                   else
                     throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                 } else if (entry.getValue().isJsonArray()) {
                     instance.putAdditionalProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                 } else { // JSON object
                     instance.putAdditionalProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                 }
               }
             }
             return instance;
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ObjectCopyCreation given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ObjectCopyCreation
  * @throws IOException if the JSON string is invalid with respect to ObjectCopyCreation
  */
  public static ObjectCopyCreation fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ObjectCopyCreation.class);
  }

 /**
  * Convert an instance of ObjectCopyCreation to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

