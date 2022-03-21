package com.dmakarevich.yellow_collector.db.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "error-info-settings")
public class ErrorInfoSettings {

    private String configName;
    private String serverStackHash;
    private String clientStackHash;
    private boolean needSendReport;
    private String userMessage ;
    private int dumpType;

    public static ErrorInfoSettings getDefault(){

        var defaultSettings = new ErrorInfoSettings();
        defaultSettings.setNeedSendReport(true);

        return defaultSettings;

    }

}
