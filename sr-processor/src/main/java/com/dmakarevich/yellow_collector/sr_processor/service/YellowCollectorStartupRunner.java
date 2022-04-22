package com.dmakarevich.yellow_collector.sr_processor.service;

import com.dmakarevich.yellow_collector.sr_processor.configs.ReportProcessorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class YellowCollectorStartupRunner implements ApplicationRunner {

    private final ReportProcessorProperties properties;

    public YellowCollectorStartupRunner(ReportProcessorProperties properties) {
        this.properties = properties;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initReportsStoragePaths();
    }

    //region service

    private void initReportsStoragePaths() {

        createStorageDirectoryIfNecessary(properties.getReportStorageLocation());
        createStorageDirectoryIfNecessary(properties.getReportErrorStorageLocation());
        createStorageDirectoryIfNecessary(properties.getReportTempStorageLocation());

    }

    private void createStorageDirectoryIfNecessary(Path path) {

        try {
            Files.createDirectories(path);
            log.info("The storage directory {} verified", path.toAbsolutePath());
        } catch (IOException e) {
            log.info("The storage directory {} verified fail", path.toAbsolutePath());
        }

    }

    //

}
