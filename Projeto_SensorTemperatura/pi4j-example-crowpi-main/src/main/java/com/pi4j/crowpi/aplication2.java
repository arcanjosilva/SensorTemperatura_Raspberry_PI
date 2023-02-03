package com.pi4j.crowpi;

import com.microsoft.azure.sdk.iot.device.DeviceTwin.*;
import com.microsoft.azure.sdk.iot.device.edge.MethodResult;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Exception;
import java.util.concurrent.TimeUnit;


public class aplication2 extends JFrame {
    private JPanel panel1;
    private JButton ButtonSetTempMore;
    private JButton ButtonSetTempLess;
    private JButton ButtonSetHumLess;
    private JButton ButtonSetHumMore;
    private JButton buttonON;
    private JTextArea textArea1;

    // Connection string for your IoT Hub

    public static final String iotHubConnectionString = "HostName=ficha9IoT.azure-devices.net;SharedAccessKeyName=service;SharedAccessKey=LvZFBRGzgrnqi/CP0xbCFGi5+pSUWDT7yENgFQ7kbfU=";

    // Device to call direct method on.
    public static final String deviceId = "mydevice";


    public static final String methodName = "SetTelemetryInterval";
    public static final String  methodON = "ON";
    public static final String  methodOFF = "OFF";
    public static final String  methodSetHumi= "SetHumi";
    public static final String  methodSetTemp= "SetTemp";


    public static final int payload = 10; // Number of seconds for telemetry interval.

    public static final Long responseTimeout = TimeUnit.SECONDS.toSeconds(30);
    public static final Long connectTimeout = TimeUnit.SECONDS.toSeconds(5);

    // Create a DeviceMethod instance to call a direct method.


    // Call the direct method.
    //MethodResult result = methodClient.invoke(deviceId, methodName, responseTimeout, connectTimeout, payload);
    public aplication2(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();


        //DeviceMethod methodClient = DeviceMethod.createFromConnectionString(iotHubConnectionString);



        ButtonSetTempMore.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //MethodResult result = methodClient.invoke(deviceId, methodSetTemp, responseTimeout, connectTimeout, payload);
            }
        });

        ButtonSetTempLess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MethodResult result = methodClient.invoke(deviceId, methodSetTemp, responseTimeout, connectTimeout, payload);

            }
        });
        ButtonSetHumLess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MethodResult result = methodClient.invoke(deviceId, methodSetHumi, responseTimeout, connectTimeout, payload);
            }
        });
        ButtonSetHumMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MethodResult result = methodClient.invoke(deviceId, methodSetHumi, responseTimeout, connectTimeout, payload);

            }
        });
        buttonON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MethodResult result = methodClient.invoke(deviceId, methodON, responseTimeout, connectTimeout, payload);
            }
        });
    }


    public static void main(String[] args) throws Exception {

        aplication2 frame = new aplication2("PROJETO");
        frame.setVisible(true);



        System.out.println("Press ENTER to exit.");
        System.in.read();
        System.out.println("Shutting down...");
    }
}


