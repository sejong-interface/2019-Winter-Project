package kr.or.teamserver.coinserver.Model;

public class ArduinoModel2 {

    private long id;
    private long amountElectric;
    private String time;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmountElectric() {
        return amountElectric;
    }

    public void setAmountElectric(long amountElectric) {
        this.amountElectric = amountElectric;
    }

    public String getTime() { return time; }

    public void setTime(String time) {
        this.time = time;
    }

    public String returnState(String state) {
        if(this.getAmountElectric() >= 10) {
            this.state = "사용중";
        }

        else if(this.getAmountElectric() >= 5) {
            this.state = "사용안함";
        }

        else {
            this.state = "고장";
        }

        return state;
    }
}