package com.syntaxnow.mock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Device {
    private String id;
    private String deviceSerialNo;
    private String deviceName;
    private String deviceModelNo;
    private String deviceFirmwareVersion;
    private String imei;
    private String iccid;
    private String connectionStatus;
    private String failoverStatus;
    private String servicePlanStatus;
    private String projectName;
    private String cellOperator;
    private String voiceLinkId;
    private String routerId;
}
