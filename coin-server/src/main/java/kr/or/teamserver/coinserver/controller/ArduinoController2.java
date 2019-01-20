package kr.or.teamserver.coinserver.controller;

import kr.or.teamserver.coinserver.Model.ArduinoModel2;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class ArduinoController2 {
    ArduinoModel2 arduinoModel2;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ArduinoModel2 sendData() {
        return this.arduinoModel2;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ArduinoModel2 getData(@RequestBody ArduinoModel2 arduinoModel2) {

        if(arduinoModel2.getAmountElectric() >= 10) {
            arduinoModel2.setState("사용중");
        }

        else if(arduinoModel2.getAmountElectric() >= 4) {
            arduinoModel2.setState("사용안함");
        }

        else {
            arduinoModel2.setState("고장");
        }

        this.arduinoModel2 = arduinoModel2;

        return this.arduinoModel2;
    }
}