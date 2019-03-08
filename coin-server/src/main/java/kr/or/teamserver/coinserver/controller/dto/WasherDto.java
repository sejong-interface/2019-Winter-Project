package kr.or.teamserver.coinserver.controller.dto;

import kr.or.teamserver.coinserver.model.Washer;

import java.time.LocalDateTime;

public class WasherDto {

    private final long id;
    private final long electricPower;
    private final LocalDateTime measuredAt;

    public WasherDto(long id, long electricPower, LocalDateTime measuredAt) {
        this.id = id;
        this.electricPower = electricPower;
        this.measuredAt = measuredAt;
    }

    public static WasherDto from(Washer washer) {
        return new WasherDto(washer.getId(), washer.getElectricPower(), washer.getMeasuredAt());
    }

    public long getId() {
        return id;
    }

    public long getElectricPower() {
        return electricPower;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    public State getState() {
        if (isOff()) {
            return State.OFF;
        } else if (isOn()) {
            return State.ON;
        } else if (isRunning()) {
            return State.RUN;
        } else {
            return State.BREAK;
        }
    }

    private boolean isOff() {
        return electricPower < 750;
    }

    private boolean isOn() {
        return 750 <= electricPower && electricPower<= 3000;
    }

    private boolean isRunning() {
        return 3000< electricPower ;
    }

    @Override
    public String toString() {
        return "WasherDto{" +
                "id=" + id +
                ", electricPower=" + electricPower +
                ", measuredAt=" + measuredAt +
                "}";
    }

    enum State {
        OFF, ON, RUN, BREAK
    }
}
