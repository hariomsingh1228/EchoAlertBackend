package com.echoalert.backend.service;

import com.echoalert.backend.entity.Alert;
import com.echoalert.backend.entity.PushToken;
import com.echoalert.backend.repository.PushTokenRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class NotificationService {

    private static final String EXPO_PUSH_URL = "https://exp.host/--/api/v2/push/send";

    private final PushTokenRepository pushTokenRepository;
    private final RestTemplate restTemplate;

    public NotificationService(PushTokenRepository pushTokenRepository) {
        this.pushTokenRepository = pushTokenRepository;
        this.restTemplate = new RestTemplate();
    }

    public boolean sendAlertNotification(Alert alert) {
        try {
            List<String> validTokens = pushTokenRepository.findAll()
                    .stream()
                    .map(PushToken::getToken)
                    .filter(this::isValidExpoPushToken)
                    .distinct()
                    .toList();

            if (validTokens.isEmpty()) {
                System.out.println("❌ No valid Expo push tokens found");
                return false;
            }

            System.out.println("📱 Found " + validTokens.size() + " unique push token(s)");

            List<Map<String, Object>> messages = new ArrayList<>();

            for (String token : validTokens) {
                Map<String, Object> data = new HashMap<>();
                data.put("alertId", alert.getId());
                data.put("uuid", alert.getUuid());
                data.put("priority", alert.getPriority());

                Map<String, Object> message = new HashMap<>();
                message.put("to", token);
                message.put("sound", "default");
                message.put("title", "🚨 " + alert.getTitle());
                message.put("body", alert.getMessage());
                message.put("data", data);

                messages.add(message);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.set("Accept-Encoding", "gzip, deflate");

            HttpEntity<List<Map<String, Object>>> request =
                    new HttpEntity<>(messages, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    EXPO_PUSH_URL,
                    request,
                    String.class
            );

            System.out.println("📩 Expo push response:");
            System.out.println(response.getBody());

            return response.getStatusCode().is2xxSuccessful();

        } catch (Exception error) {
            System.out.println("❌ Push notification error: " + error.getMessage());
            return false;
        }
    }

    private boolean isValidExpoPushToken(String token) {
        return token != null &&
                (token.startsWith("ExponentPushToken[") ||
                        token.startsWith("ExpoPushToken["));
    }
}