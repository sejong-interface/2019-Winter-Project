package kr.or.teamserver.coinserver.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AndroidPushPeriodicNotifications {

    public static String PeriodicNotificationJson() throws JSONException {
        LocalDate localDate = LocalDate.now();

        String[][] sampleData = {
                {"cn1uFGx9Hwk:APA91bEaXlI7B_x20mtgKPDsC5fY5f_7RpftCunsJ4wLoOe04vsvLajopAEQqf8csFBB9oBOnqCUjOWQT1BfOumWyoypd7o0lTlGouV4CiSBZuu3Ha8pU6deQg9nqI06ZABS9GNKXrq2","FRIDAY"}
                ,{"dzi7HKEgSls:APA91bFcuxoC83A9y5o616fY_DKp8nz_4Be6YqymH3EuAzPwRXu1Bhi2eHhTPM2u_pOvEf-hJmxW-Hkub5EuZY-XLVm89JX7pXbngQgm5vsfyTCKMoxFg_SR_YUimnjATSkiS89446la","SUNDAY"}
        }; // {이식,하늬}

        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<sampleData.length; i++){
            if (sampleData[i][1] == "FRIDAY"){
                tokenlist.add(sampleData[i][0]);
            } //"FRIDAY" >> localDate.getDayOfWeek().name()
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();
        notification.put("title","Today is "+localDate.getDayOfWeek().name()+"!");
        notification.put("body","It is a day to wash clothes.");

        body.put("notification", notification);
        System.out.println(body.toString());

        return body.toString();
    }
}
