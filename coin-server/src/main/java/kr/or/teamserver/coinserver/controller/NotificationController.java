package kr.or.teamserver.coinserver.controller;

import java.util.Observable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import kr.or.teamserver.coinserver.service.AndroidPushPeriodicNotifications;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import kr.or.teamserver.coinserver.service.AndroidPushNotificationsService;

@RestController
public class NotificationController {

    static Logger logger = Logger.getLogger(NotificationController.class.getName());

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @Scheduled(fixedRate = 5000) //5초 > 실제 서버 동작 시 1000*60*60*24 ( 24시간 )
    @GetMapping(value = "/send")
    public @ResponseBody ResponseEntity<String> send() throws JSONException, InterruptedException  {
        String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJson();

        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try{
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        }
        catch (InterruptedException e){
            logger.debug("got interrupted!");
            throw new InterruptedException();
        }
        catch (ExecutionException e){
            logger.debug("execution error!");
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }

}
