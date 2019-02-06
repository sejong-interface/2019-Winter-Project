package kr.or.infe.washer.controller;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import kr.or.infe.washer.service.AndroidPushNotificationsService;

@RestController
public class WebController {
    //private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> send() throws JSONException{

        //FCM 메시지 전송//
        //단일 전송//

        JSONObject body = new JSONObject();
        /*
        //DB에 저장된 여러개의 토큰(수신자)을 가져와서 설정할 수 있다.//
        List<String> tokenlist = new ArrayList<String>();
        //DB과정 생략(직접 대입)//
        tokenlist.add("token value 1");
        tokenlist.add("token value 2");
        tokenlist.add("token value 3");

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array); //여러개의 메시지일 경우 registration_ids, 단일 메세지는 to사용//
        */

        JSONObject notification = new JSONObject();
        notification.put("title", "FCM SERVER TEST");
        notification.put("body", "happy message!");
        //notification.put("body",paramInfo.get("message"));

        body.put("notification", notification);
        body.put ("to","cl94GyxkK8c:APA91bHctXWi1zTEaEj1DEEnq5iRwyRG7vRtSoov-73qhpJdFRxWrO06ntAO9Ab7Lz_Yq0XG2yNf8UE0HCs2cnf-Ih-lvqfuuhw7lSRZFa71uZoGhI2B3ITFnWywLX3D1LpGrM5CY2at");

        System.out.println(body.toString());

        HttpEntity<String> request = new HttpEntity<>(body.toString());

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
}
