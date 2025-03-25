package com.example.mymod;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AnalyticsTracker {
    private static final String MEASUREMENT_ID = "G-BYVSHQTEFN";  // Replace with your GA4 ID
    private static final String API_SECRET = "4yDa7NvrT_aqSCfcW6mk";  // Replace with your secret

    public static void sendEvent(String eventName, String playerName) {
        try {
            URL url = new URL("https://www.google-analytics.com/mp/collect?measurement_id=" + MEASUREMENT_ID + "&api_secret=" + API_SECRET);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payload = "{ \"client_id\": \"" + playerName + "\", \"events\": [{ \"name\": \"" + eventName + "\", \"params\": { \"player\": \"" + playerName + "\" } }] }";
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("GA Event Sent: " + eventName + " | Response Code: " + responseCode);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
