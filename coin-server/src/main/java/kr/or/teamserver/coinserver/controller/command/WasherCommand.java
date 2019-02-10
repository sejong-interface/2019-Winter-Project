package kr.or.teamserver.coinserver.controller.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WasherCommand {

    private final long electricPower;

    @JsonCreator
    public WasherCommand(@JsonProperty("electricPower") long electricPower) {
        this.electricPower = electricPower;
    }

    public long getElectricPower() {
        return electricPower;
    }
}
