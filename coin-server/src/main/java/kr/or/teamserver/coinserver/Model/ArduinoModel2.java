package kr.or.teamserver.coinserver.Model;

public class ArduinoModel2 {

    private long washerId;
    private long AmountElectric;
    private String Time;
    private String State;

    public long getWasherId() {
        return washerId;
    }

    public void setWasherId(long washerId) {
        this.washerId = washerId;
    }

    public long getAmountElectric() {
        return AmountElectric;
    }

    public void setAmountElectric(long AmountElectric) {
        this.AmountElectric = AmountElectric;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }
}