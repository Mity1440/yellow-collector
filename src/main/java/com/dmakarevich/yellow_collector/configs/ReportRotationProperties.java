package com.dmakarevich.yellow_collector.configs;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "yellow-collector.rotation")
public class ReportRotationProperties {

    private int oldReports;
    private int errorReports;
    private String purgeReportsCron;

}
