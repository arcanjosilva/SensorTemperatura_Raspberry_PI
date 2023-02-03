package com.pi4j.crowpi.azure;

import com.microsoft.azure.sdk.iot.device.DeviceTwin.DeviceMethodCallback;
import com.microsoft.azure.sdk.iot.device.DeviceTwin.DeviceMethodData;


import com.pi4j.crowpi.TestPi;

public class DirectMethodCallback implements DeviceMethodCallback {
    private static final int METHOD_SUCCESS = 200;
    private static final int METHOD_NOT_DEFINED = 404;
    private static final int INVALID_PARAMETER = 400;
    private final TestPi pi;

    private static String showHumiText;


    //private static int telemetryInterval = 1000;


    private void setTelemetryInterval(int interval) {
        System.out.println("Direct method # Setting telemetry interval (seconds): " + interval);

    }

    private void On() {

    }

    private void Off() {

    }

    private void SetTemp(int interval) {
    }


    private void SetHumi(int interval) {
        System.out.println("Direct method # Set Humidification");
    }

    public DirectMethodCallback(TestPi pi) {
        this.pi = pi;
    }


    // MUDAR AQUI
    @Override
    public DeviceMethodData call(String methodName, Object methodData, Object context) {
        DeviceMethodData deviceMethodData;
        String payload = new String((byte[]) methodData);

        int interval;
        switch (methodName)
        {
            case "SetTelemetryInterval" :
            {

                try {
                    int status = METHOD_SUCCESS;
                    interval = Integer.parseInt(payload);
                    System.out.println(payload);
                    setTelemetryInterval(interval);
                    deviceMethodData = new DeviceMethodData(status, "Executed direct method " + methodName);
                } catch (NumberFormatException e) {
                    int status = INVALID_PARAMETER;
                    deviceMethodData = new DeviceMethodData(status, "Invalid parameter " + payload);
                }
                break;

            }
            case "ON":
            {
                int status = METHOD_SUCCESS;
                System.out.println(payload);
                deviceMethodData = new DeviceMethodData(status, "Ligar o dispositivo");
                break;
            }
            case "OFF": {
                int status = METHOD_SUCCESS;
                System.out.println(payload);
                deviceMethodData = new DeviceMethodData(status, "Desligar dispositivo");
                break;
            }

            case "SetHumi" :
            {
                try {
                    int status = METHOD_SUCCESS;
                    interval = Integer.parseInt(payload);
                    System.out.println(payload);
                    SetHumi(interval);
                    deviceMethodData = new DeviceMethodData(status, "Executed direct method " + methodName);
                } catch (NumberFormatException e) {
                    int status = INVALID_PARAMETER;
                    deviceMethodData = new DeviceMethodData(status, "Invalid parameter " + payload);
                }
                break;
            }
            case "SetTemp" :
            {
                try {
                    int status = METHOD_SUCCESS;
                    interval = Integer.parseInt(payload);
                    System.out.println(payload);
                    SetTemp(interval);
                    deviceMethodData = new DeviceMethodData(status, "Executed direct method " + methodName);
                } catch (NumberFormatException e) {
                    int status = INVALID_PARAMETER;
                    deviceMethodData = new DeviceMethodData(status, "Invalid parameter " + payload);
                }
                break;
            }
            default:
            {
                int status = METHOD_NOT_DEFINED;
                deviceMethodData = new DeviceMethodData(status, "Not defined direct method " + methodName);
            }
        }

        return deviceMethodData;
    }


}
