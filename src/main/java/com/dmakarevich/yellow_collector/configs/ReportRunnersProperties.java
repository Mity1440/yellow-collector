package com.dmakarevich.yellow_collector.configs;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "yellow-collector.runners")
public class ReportRunnersProperties {

    private int workerPoolSize;
    private int maximumFileProcessingQueueSizePerIteration;

    public int getWorkerPoolSize() {
        return workerPoolSize == 0 ? 5: workerPoolSize;
    }

    public int getMaximumFileProcessingQueueSizePerIteration() {
        return maximumFileProcessingQueueSizePerIteration == 0? 100 : maximumFileProcessingQueueSizePerIteration;
    }

}
