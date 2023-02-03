package com.pi4j.crowpi;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;

import com.pi4j.Pi4J;
import com.pi4j.crowpi.azure.DirectMethodCallback;
import com.pi4j.crowpi.azure.DirectMethodStatusCallback;
import com.pi4j.crowpi.azure.MessageSender;
import com.pi4j.crowpi.components.HumiTempComponent;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;

import java.util.Date;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPi {

    private static final int PIN_BUTTON = 24; // PIN 18 = BCM 24
    private static final int PIN_LED = 22; // PIN 15 = BCM 22

    public static int pressCount = 0;


    private static String connString = "HostName=ficha9IoT.azure-devices.net;DeviceId=mydevice;SharedAccessKey=oAWWjLykU8LsFrCTOVP2l3DOqO/QZGHWqay0Ukhmlb4=";

    // Using the MQTT protocol to connect to IoT Hub
    private static IotHubClientProtocol protocol = IotHubClientProtocol.MQTT;
    private static DeviceClient client;


    public static void main(String[] args) throws Exception {


        TestPi pi = new TestPi();
        // Connect to the IoT hub.

        client = new DeviceClient(connString, protocol);
        client.open();
        client.subscribeToDeviceMethod(new DirectMethodCallback(pi), null, new DirectMethodStatusCallback(), null);


        HumiTempComponent dht11 = new HumiTempComponent();
        Date date = new Date();

        HumiTempComponent Sensor = new HumiTempComponent();

        // Create new thread and start sending messages
        MessageSender sender = new MessageSender(client, dht11,date,Sensor);

        //ledState -> no construtor

        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(sender);
        // Create Pi4J console wrapper/helper


        var pi4j = Pi4J.newAutoContext();

        var ledConfig = DigitalOutput.newConfigBuilder(pi4j).id("led").name("LED Flasher").address(PIN_LED).shutdown(DigitalState.LOW).initial(DigitalState.LOW).provider("pigpio-digital-output");
        var led = pi4j.create(ledConfig);

        var buttonConfig = DigitalInput.newConfigBuilder(pi4j).id("button").name("Press button").address(PIN_BUTTON).pull(PullResistance.PULL_DOWN).debounce(3000L).provider("pigpio-digital-input");

        var button = pi4j.create(buttonConfig);


        button.addListener(e -> {
            if (e.state() == DigitalState.LOW) {
                pressCount++;
            }
        });

        while (pressCount < 50) {
            if (pressCount % 2 == 0) {
                led.high();
            } else
                led.low();

        }
        Thread.sleep(500);

        pi4j.shutdown();
    }
}
