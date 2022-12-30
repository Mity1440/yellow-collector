package com.dmakarevich.yellow_collector.sr_receiver.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "yellow-collector.sr-receiver.storage")
public class StorageProperties {

    private String temp;

    public String getTemp() {
        return temp == null || temp.strip().isEmpty() ? "temp/buffer": temp;
    }

}