package kr.or.teamserver.coinserver.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import kr.or.teamserver.coinserver.controller.ArduinoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AndroidPushNotificationsService {

    private static final String firebase_server_key="AAAAV-81EgQ:APA91bFtrE9nDAs-k8Xj9YaL4QBfKkzpIQn29xULQAUx56Q_AfKnR0yPw3HZ80vDulOwUCV04FA2w6Xdy8kLT8hoB91KxDCzHQQbhFpIRzUqcRe49RsQ1NBHv1ZdJlHkM-A91uJ5HNzO";
    private static final String firebase_api_url="https://fcm.googleapis.com/fcm/send";
    private final Logger logger = LoggerFactory.getLogger(AndroidPushNotificationsService.class);

    //https://fcm.googleapis.com/v1/projects/washer-d0304/messages:send
    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new HeaderRequestInterceptor("Authorization",  "key=" + firebase_server_key));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json; UTF-8 "));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(firebase_api_url, entity, String.class);
        logger.info("{}",firebaseResponse);
        return CompletableFuture.completedFuture(firebaseResponse);
    }

}