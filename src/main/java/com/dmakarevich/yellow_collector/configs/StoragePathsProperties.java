package com.dmakarevich.yellow_collector.configs;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "yellow-collector.storage.paths")
public class StoragePathsProperties{

    private String temp;
    private String root;
    private String error;

}
