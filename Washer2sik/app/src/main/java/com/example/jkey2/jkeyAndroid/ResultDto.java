package com.example.jkey2.jkeyAndroid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.util.CollectionUtils;

import java.util.List;

public class ResultDto {

  private final int code;
  private final String message;
  private final List<WasherDto> items;

  @JsonCreator
  public ResultDto(@JsonProperty("code") int code, @JsonProperty("message")String message,@JsonProperty("items") List<WasherDto> items) {
    this.code = code;
    this.message = message;
    this.items = items;
  }
/*
  public static <T> ResultDto succeed(T data) {
    return new ResultDto(200, "정상적으로 처리되었습니다.", List.of(data));
  }
*/

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public List<WasherDto> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return "ResultDto{" +
            "code=" + code +
            ", message='" + message + '\'' +
            ", items=" + items +
            "}";
  }
}
