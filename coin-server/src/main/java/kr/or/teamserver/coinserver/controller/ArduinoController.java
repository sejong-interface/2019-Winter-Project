package kr.or.teamserver.coinserver.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.teamserver.coinserver.controller.command.WasherCommand;
import kr.or.teamserver.coinserver.controller.dto.ResultDto;
import kr.or.teamserver.coinserver.controller.dto.WasherDto;
import kr.or.teamserver.coinserver.model.Washer;
import kr.or.teamserver.coinserver.service.AndroidPushNotificationsService;
import kr.or.teamserver.coinserver.service.WasherService;

@RestController
@RequestMapping("/arduino")
public class ArduinoController {

    private final Logger logger = LoggerFactory.getLogger(ArduinoController.class);
    private final WasherService washerService;
    private final AndroidPushNotificationsService androidPushNotificationsService;

    @Autowired
    public ArduinoController(WasherService washerService, AndroidPushNotificationsService androidPushNotificationsService) {
        this.washerService = washerService;
        this.androidPushNotificationsService = androidPushNotificationsService;
    }

    @PostMapping({ "", "/" })
    public ResultDto<WasherDto> save(@RequestBody WasherCommand washerCommand) {
        Washer washer = washerService.save(washerCommand.getElectricPower());
        return ResultDto.succeed(WasherDto.from(washer));
    }

    @PostMapping("/{id}")
    public ResultDto<WasherDto> save(@PathVariable long id, @RequestBody WasherCommand washerCommand) {
        Washer prevWasher = washerService.findOne(id);
        WasherDto prevWasherDto = WasherDto.from(prevWasher);

        Washer washer = washerService.save(id, washerCommand.getElectricPower());
        WasherDto washerDto = WasherDto.from(washer);

        if (prevWasherDto.getState() != washerDto.getState()) {
            JSONObject body = new JSONObject();
            JSONObject notification = new JSONObject();

            notification.put("title", "washer state is updated");
            notification.put("body", "id : " + id + "  " + washerDto.getState());

            String[] tokens = {
                    "erdMYnB-uRQ" +
                    ":APA91bHohvHFcYRTQMIDFkKr_vvZhzojBMHtIP5OGXx82Qb7OLoLAGa2iSMOSXDCq1MPLyQB11BJhFyzubFY10GANBHdy" +
                    "-okLQS5wzUB62XOhuL4AMjnfr_GXaEWjYO33hjp7lmXl2jl"
            };
            body.put("registration_ids", tokens);
            body.put("notification", notification);
            HttpEntity<String> request = new HttpEntity<>(body.toString());

            androidPushNotificationsService.send(request);
        }

        return ResultDto.succeed(WasherDto.from(washer));
    }
}
