package com.dmakarevich.yellow_collector.sr_processor.configs;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "yellow-collector.sr-processor")
public class ReportProcessorProperties {

    public Map<String, String> runners;
    public Map<String, String> rotation;
    public Map<String, String> storage;

    //region Storage

    public Path getReportStorageLocation(){
        return Path.of(getStringReportStorageLocation("root").orElse("temp/main"));
    }

    public Path getReportTempStorageLocation(){
        return Path.of(getStringReportStorageLocation("temp").orElse("temp/buffer"));
    }

    public Path getReportErrorStorageLocation(){
        return Path.of(getStringReportStorageLocation("error").orElse("temp/error"));
    }

    public Optional<String> getStringReportStorageLocation(String locationName){
        if (storage != null){
            return Optional.ofNullable(storage.get(locationName));
        }
        return Optional.empty();
    }

    //endregion

    //region Rotation

    public int getFileExistenceInRootLocationInDays(){
        if (rotation != null){
            return tryParseInt(
                    rotation.get("root-location-in-days"), 0);
        }
        return 0;
    }

    public int getFileExistenceInErrorLocationInDays(){
        if (rotation != null){
            return tryParseInt(
                    rotation.get("error-location-in-days"), 0);
        }
        return 0;
    }

    //endregion

    //region Runners

    public int getRunnersWorkerPoolSize(){
        if (runners != null){
            return tryParseInt(
                    runners.get("worker-pool-size"), 1);
        }

        return 1;
    }

    public int getSizeOfReportProcessingQueue(){
        if (runners != null){
            return tryParseInt(
                    runners.get("size-of-report-processing-queue"), 100);
        }

        return 100;
    }

    public int getTimeoutOnReportProcessingIteration(){
        if (runners != null){
            return tryParseInt(
                    runners.get("timeout-on-report-processing-iteration-in-minutes"), 5);
        }

        return 5;
    }

    //endregion

    //region Service

    public int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    //endregion

}
