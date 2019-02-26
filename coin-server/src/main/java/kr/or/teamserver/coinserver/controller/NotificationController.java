package kr.or.teamserver.coinserver.controller;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.Calendar;

import kr.or.teamserver.coinserver.controller.dto.DeviceDto;
import kr.or.teamserver.coinserver.service.AndroidPushPeriodicNotifications;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.teamserver.coinserver.service.AndroidPushNotificationsService;

@RestController
public class NotificationController {

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;
    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @GetMapping(value = "/send", produces = "application/json")
    public @ResponseBody ResponseEntity<String> send() throws JSONException {
        String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJson();

        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/device")
    public void deviceData (@RequestBody DeviceDto deviceDto){
        logger.info("token : "+deviceDto.token);
        logger.info("date : "+deviceDto.date);
    }
}
