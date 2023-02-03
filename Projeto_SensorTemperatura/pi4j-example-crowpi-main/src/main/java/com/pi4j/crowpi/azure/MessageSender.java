package com.pi4j.crowpi.azure;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.Message;
import com.pi4j.crowpi.TestPi;
import com.pi4j.crowpi.components.HumiTempComponent;


import java.util.Date;

public class MessageSender implements Runnable {
    private DeviceClient client;
    private HumiTempComponent dht11;
    private HumiTempComponent Sensor;

    private Date date;

    public MessageSender(DeviceClient client, HumiTempComponent dht11, Date date, HumiTempComponent Sensor) {
        this.client = client;
        this.dht11 = dht11;
        this.date = date;
        this.Sensor = Sensor;
    }


    @Override

    public void run() {
        try {
            while (true) {


                Date date = new Date();
                HumiTempComponent Sensor = new HumiTempComponent();
                TelemetryDataPoint telemetryDataPoint = new TelemetryDataPoint();
                telemetryDataPoint.temperature = dht11.getTemperature();
                telemetryDataPoint.humidity = dht11.getHumidity();
                telemetryDataPoint.date = date;
                telemetryDataPoint.Sensor = Sensor.getClass().getSimpleName();


                // Add the telemetry to the message body as JSON.

                String msgStr = telemetryDataPoint.serialize();


                Message msg = new Message("Sensor ON: " + " " +msgStr);
                Message msg2 = new Message("Sensor OFF - "+ Sensor.getClass().getSimpleName() + " " + date);


                if (TestPi.pressCount % 2 == 0) {

                    System.out.println("Sensor" + msgStr);


                    Object lockobj = new Object();

                    // Send the message.
                    EventCallback callback = new EventCallback();
                    client.sendEventAsync(msg, callback, lockobj);


                    synchronized (lockobj) {
                        lockobj.wait();
                    }
                }
                else {

                    Object lockobj = new Object();

                    EventCallback callback = new EventCallback();
                    client.sendEventAsync(msg2, callback, lockobj);

                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}