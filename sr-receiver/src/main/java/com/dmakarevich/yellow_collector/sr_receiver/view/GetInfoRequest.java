package com.dmakarevich.yellow_collector.sr_receiver.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInfoRequest {

    private String appName;
    private String appStackHash;
    private String appVersion;
    private String clientID;
    private String clientStackHash;
    private String configHash;
    private String configName;
    private String configurationInterfaceLanguageCode;
    private String configVersion;
    private List<String> errorCategories = new ArrayList<>();
    private String platformInterfaceLanguageCode;
    private String platformType;
    private String reportID;
    private String serverStackHash;
    private String systemCrash;

}
