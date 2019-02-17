package com.example.jkey2.jkeyAndroid;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class WasherDto {

  private  long id;
  private  long electricPower;
  private  String measuredAt;
  private String state;

  @JsonCreator
  public WasherDto(@JsonProperty("id") long id,
                   @JsonProperty("electricPower") long electricPower,
                   @JsonProperty("measuredAt") String measuredAt,
                   @JsonProperty("state") String state) {
    this.id = id;
    this.electricPower = electricPower;
    this.measuredAt = measuredAt;
    this.state = state;
  }

  public long getId() {
    return id;
  }

  public long getElectricPower() {
    return electricPower;
  }

  public String getMeasuredAt()
  {
    return measuredAt;
  }

  public String getState()
  {
    return state;
  }
}