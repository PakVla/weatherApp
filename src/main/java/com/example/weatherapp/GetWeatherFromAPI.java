package com.example.weatherapp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class GetWeatherFromAPI {
    private String urlAPI = "https://api.openweathermap.org/data/2.5/weather?";
    private String keyAPI;

    public String getKeyAPI() { return keyAPI; }
    public void setKeyAPI(String keyAPI) { this.keyAPI = keyAPI; }

    public GetWeatherFromAPI(String keyAPI){
        this.keyAPI = keyAPI;
    }

    public String getDataFromAPI(String city) throws IOException {
        URL weatherApi = new URL(urlAPI + "q=" + city + "&appid=" + keyAPI + "&units=metric");
        HttpURLConnection connectionToAPI = (HttpURLConnection) weatherApi.openConnection();
        connectionToAPI.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connectionToAPI.getInputStream()));
        return reader.readLine();
    }
}
