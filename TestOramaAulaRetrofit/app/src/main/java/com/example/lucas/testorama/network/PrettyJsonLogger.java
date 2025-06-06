package com.example.lucas.testorama.network;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class PrettyJsonLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String prettyJson = gson.toJson(JsonParser.parseString(message));
                Log.d("PRETTY_JSON", prettyJson);
            } catch (JsonSyntaxException e) {
                Log.d("PRETTY_JSON", message); // fallback
            }
        } else {
            Log.d("PRETTY_JSON", message);
        }
    }
}

