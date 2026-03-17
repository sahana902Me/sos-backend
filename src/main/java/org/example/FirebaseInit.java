package org.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;

public class FirebaseInit {

    public static void init() throws Exception {

        if (FirebaseApp.getApps().isEmpty()) {

            try (FileInputStream serviceAccount =
                         new FileInputStream("src/main/resources/serviceAccountKey.json")) {

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
            }
        }
    }
}