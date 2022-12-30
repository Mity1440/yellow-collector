package com.dmakarevich.yellow_collector.sr_processor.service;

import com.dmakarevich.yellow_collector.sr_processor.config.ReportProcessorProperties;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.dmakarevich.yellow_collector.sr_processor.report.FileArchiveUtils.*;

@Slf4j
@Component
public class ArchiveRotator{

    private final ReportProcessorProperties properties;

    public ArchiveRotator(ReportProcessorProperties properties) throws IOException {
        this.properties = properties;
    }


    @Scheduled(cron = "${storage.rotation.purge-reports.cron: 0 0 12 * * *}")
    public void rotate() throws IOException {

        cleanUpDirectory(properties.getReportStorageLocation(), properties.getFileExistenceInRootLocationInDays());
        cleanUpDirectory(properties.getReportErrorStorageLocation(), properties.getFileExistenceInErrorLocationInDays());

    }

}
