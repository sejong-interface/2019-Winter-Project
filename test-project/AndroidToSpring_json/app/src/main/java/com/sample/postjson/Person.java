package com.sample.postjson;

/**
 * Created by Administrator on 2016-06-07.
 */
public class Person {
    private String token; //name
    private String date; //country

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date= date;
    }



    @Override
    public String toString() {
        return "Person [name=" + name + ", country=" + country + "]";
    }
}
