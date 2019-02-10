package kr.or.teamserver.coinserver.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AndroidPushNotificationsService {

    private static final String firebase_server_key="AAAACMhceLs:APA91bG3mCWwIGTwT_7-cYiXm5iyzCbmyUi6wZmDFeHZxvDRqOXtJ1fe1ADNt7N_uYpY1mK_zWHRUlPoETRvn8PXH3zF_eUvsggaChPqeIvPLF_GYW1rTzZlfuUUvnU0TQlIig9S2wo3";
    private static final String firebase_api_url="https://fcm.googleapis.com/fcm/send";

    //https://fcm.googleapis.com/v1/projects/washer-d0304/messages:send
    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new HeaderRequestInterceptor("Authorization",  "key=" + firebase_server_key));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json; UTF-8 "));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(firebase_api_url, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }

}