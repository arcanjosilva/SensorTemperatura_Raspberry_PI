package com.pi4j.crowpi.azure;

import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;

public class DirectMethodStatusCallback implements IotHubEventCallback {
    public void execute(IotHubStatusCode status, Object context) {
        System.out.println("Direct method # IoT Hub responded to device method acknowledgement with status: " + status.name());
    }
}