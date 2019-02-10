package com.example.jkey2.jkeyAndroid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
public class Model {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private int id;
    private String amountElectric;
    private String state;
    private String time;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getAmountElectric(){
        return amountElectric;
    }
    public void setAmountElectric(){
        this.amountElectric = amountElectric;
    }
    public String getState(){
        return state;
    }
    public void setState(){
        this.state = state;
    }
    public String getTime(){
        return time;
    }
    public void setTime(){
        this.time= time;
    }
    public String toString(){
        return this.id +"|" + this.time + "|"+this.state; //
    }
}
