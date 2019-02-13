package kr.or.teamserver.coinserver.controller.dto;

import java.time.LocalDateTime;

public class DeviceDto {
    private Integer id;
    private String token;
    private String date;

    public void setDevice(Integer id, String token, String date){
        this.id = id;
        this.token = token;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "DeviceDto{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
