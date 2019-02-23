package kr.or.teamserver.coinserver.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import kr.or.teamserver.coinserver.exception.NotificationResponseException;
import org.json.JSONException;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import kr.or.teamserver.coinserver.service.AndroidPushNotificationsService;
import kr.or.teamserver.coinserver.controller.dto.DeviceDto;
import kr.or.teamserver.coinserver.service.AndroidPushPeriodicNotifications;

@RestController
public class NotificationController {

    private final AndroidPushNotificationsService androidPushNotificationsService;

    public NotificationController(AndroidPushNotificationsService pushNotification) {
        this.androidPushNotificationsService = pushNotification;
    }

    @Scheduled(fixedRate = 20000) // 실제 서버 실행 시 24시간 주기로
    @GetMapping(value = "/send")
    public @ResponseBody ResponseEntity<String> send() throws JSONException, NotificationResponseException {
        String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJson();

        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        }
        catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new NotificationResponseException("Push Notification Response error!");
        }
        catch(ExecutionException e){
            throw new NotificationResponseException("Push Notification Response error!");
        }
    }

    @PostMapping("/device")
    public void deviceData (@RequestBody DeviceDto deviceDto){
        System.out.println("token : "+deviceDto.token);
        System.out.println("date : "+deviceDto.date);
    }
}
