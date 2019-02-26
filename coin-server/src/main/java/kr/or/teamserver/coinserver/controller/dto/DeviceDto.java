package kr.or.teamserver.coinserver.controller.dto;

public class DeviceDto {
    public String token;
    public String date;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    @Override
    public String toString()
    {
        return "DeviceDto [Date=" + date + ", token=" + token + "]";
    }
}
