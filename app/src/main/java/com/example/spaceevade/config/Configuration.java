package com.example.spaceevade.config;

import android.content.Context;

import androidx.annotation.RawRes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class Configuration {
    private static JSONObject jsonObject;

    public static void loadConfiguration(Context context, @RawRes int resourceId) {
        try {
            // Read the JSON file
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            // Convert the byte array to a JSON string
            String jsonString = new String(buffer, StandardCharsets.UTF_8);

            // Parse the JSON string
            jsonObject = new JSONObject(jsonString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static int getGameHeight() {
        if (jsonObject == null) {
            throw new IllegalStateException("Configuration not loaded. Call loadConfiguration() first.");
        }
        try {
            return Integer.parseInt(jsonObject.getString("gameHeight"));
        } catch (JSONException e) {
            return -1;
        }
    }

    public static int getNumberOfLanes() {
        if (jsonObject == null) {
            throw new IllegalStateException("Configuration not loaded. Call loadConfiguration() first.");
        }
        try {
            return Integer.parseInt(jsonObject.getString("numberOfLanes"));
        } catch (JSONException e) {
            return -1;
        }
    }

    public static int getNumberOfHealth() {
        if (jsonObject == null) {
            throw new IllegalStateException("Configuration not loaded. Call loadConfiguration() first.");
        }
        try {
            return Integer.parseInt(jsonObject.getString("numberOfHealth"));
        } catch (JSONException e) {
            return -1;
        }
    }

    public static int getDelayInMills() {
        if (jsonObject == null) {
            throw new IllegalStateException("Configuration not loaded. Call loadConfiguration() first.");
        }
        try {
            return Integer.parseInt(jsonObject.getString("delayInMills"));
        } catch (JSONException e) {
            return -1;
        }
    }
}
