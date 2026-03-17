package org.example;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

public class SendAlert {

    public static void sendAlert(String location, String token) throws Exception {

        Message message = Message.builder()
                .setToken(token)

                // DATA PAYLOAD (app will read this)
                .putData("title", "🚨 SOS ALERT")
                .putData("alert_message", "🚨 SOS ALERT! I need help")
                .putData("location_link", location)

                // HIGH PRIORITY for instant delivery
                .setAndroidConfig(
                        AndroidConfig.builder()
                                .setPriority(AndroidConfig.Priority.HIGH)
                                .build()
                )

                .build();

        FirebaseMessaging.getInstance().send(message);
    }
}