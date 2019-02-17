package kr.or.teamserver.coinserver.controller;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import kr.or.teamserver.coinserver.service.AndroidPushNotificationsService;

@RestController
public class FcmController {
    String[][] sampleData = {
            {"dzi7HKEgSls:APA91bFcuxoC83A9y5o616fY_DKp8nz_4Be6YqymH3EuAzPwRXu1Bhi2eHhTPM2u_pOvEf-hJmxW-Hkub5EuZY-XLVm89JX7pXbngQgm5vsfyTCKMoxFg_SR_YUimnjATSkiS89446la","Monday"},
    };

    Calendar cal = Calendar.getInstance();

    public String date(Calendar cal){
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        String day = "";

        if ( dayOfWeek == 1 ){ day = "Sunday"; }
        else if ( dayOfWeek ==2 ){ day = "Monday"; }
        else if ( dayOfWeek ==3 ){ day = "Tuesday"; }
        else if ( dayOfWeek ==4 ){ day = "Wednesday"; }
        else if ( dayOfWeek ==5 ){ day = "Thursday"; }
        else if ( dayOfWeek ==6 ){ day = "Friday"; }
        else if ( dayOfWeek ==7 ){ day = "Saturday"; }

        return day;
    }

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> send() throws JSONException{

        //FCM 메시지 전송//

        JSONObject body = new JSONObject();

        //단일 수신
        //body.put ("to","cl94GyxkK8c:APA91bHctXWi1zTEaEj1DEEnq5iRwyRG7vRtSoov-73qhpJdFRxWrO06ntAO9Ab7Lz_Yq0XG2yNf8UE0HCs2cnf-Ih-lvqfuuhw7lSRZFa71uZoGhI2B3ITFnWywLX3D1LpGrM5CY2at");

        //다중 수신
        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<sampleData.length; i++){
            if (sampleData[i][1] == date(cal)){
                tokenlist.add(sampleData[i][0]);
            }
        } //오늘이 사용자가 선택한 해당 요일일 때만 알림

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array); //여러개의 메시지일 경우 registration_ids, 단일 메세지는 to사용//

        JSONObject notification = new JSONObject();
        notification.put("title","Today is "+date(cal)+"!");
        notification.put("body","It is a day to wash clothes.");
        //notification.put("body",paramInfo.get("message"));

        body.put("notification", notification);

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
