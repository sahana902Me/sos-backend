package org.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

public class TokenService {

    public static List<String> getTokens() throws Exception {

        Firestore db = FirestoreClient.getFirestore();

        List<String> tokens = new ArrayList<>();

        ApiFuture<QuerySnapshot> future = db.collection("alert_tokens").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for(QueryDocumentSnapshot doc : documents){

            String token = doc.getString("token");

            if(token != null && !token.isEmpty()){
                tokens.add(token);
            }
        }

        return tokens;
    }
}