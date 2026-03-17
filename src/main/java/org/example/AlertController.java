package org.example;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AlertController {

    @PostMapping("/sendAlert")
    public String sendAlert(@RequestBody Map<String,String> data){


        try{

            System.out.println("SOS ALERT REQUEST RECEIVED");

            String location = data.get("location");

            // GET TOKENS FROM SERVICE
            List<String> tokens = TokenService.getTokens();

            for(String token : tokens){

                try{

                    SendAlert.sendAlert(location, token);

                }catch(Exception e){

                    System.out.println("Invalid token removing: " + token);

                    Firestore db = FirestoreClient.getFirestore();
                    db.collection("alert_tokens").document(token).delete();
                }
            }

            return "Alert Sent";

        }catch(Exception e){

            e.printStackTrace();
            return "Server Error";
        }
    }
}