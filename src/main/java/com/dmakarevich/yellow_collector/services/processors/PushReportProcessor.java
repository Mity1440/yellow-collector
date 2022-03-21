package com.dmakarevich.yellow_collector.services.processors;

import org.springframework.web.multipart.MultipartFile;

public interface PushReportProcessor {

    void processReportFile(MultipartFile file);

}
