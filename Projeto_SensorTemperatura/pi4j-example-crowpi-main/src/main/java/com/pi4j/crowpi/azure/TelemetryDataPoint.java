package com.pi4j.crowpi.azure;

import com.google.gson.Gson;
import com.microsoft.azure.sdk.iot.device.Message;

import java.util.Date;


public class TelemetryDataPoint {
    public double temperature;
    public double humidity;
    public  Date date;

    public String Sensor;



    public Message message;

    // Serialize object to JSON format.
    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

