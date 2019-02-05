package kr.or.teamserver.coinserver.controller;

import kr.or.teamserver.coinserver.Model.ArduinoModel2;

import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class ArduinoController2 {
    ArduinoModel2 command = new ArduinoModel2();
    private static final Logger logger = Logger.getLogger(ArduinoController2.class.getName());

    @GetMapping("/android")
    public ArduinoModel2 sendData() {
        return command;
    }

    @PostMapping("/arduino")
    public ArduinoModel2 getData(@RequestBody ArduinoModel2 arduinoModel2) {

        command = arduinoModel2;
        command.returnState();

        logger.info("Id: {" + Long.toString(command.getId()) + "}");
        logger.info("AmountElectric: {" + Long.toString(command.getAmountElectric()) + "}");
        logger.info("State: {" + command.returnState() + "}");
        logger.info("Time: {" + command.getTime() + "}");

        return command;
    }
}