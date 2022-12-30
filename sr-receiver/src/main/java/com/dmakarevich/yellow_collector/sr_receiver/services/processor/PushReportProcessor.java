package com.dmakarevich.yellow_collector.sr_receiver.services.processor;

import org.springframework.web.multipart.MultipartFile;

public interface PushReportProcessor {
    void processReportFile(MultipartFile file);
}
