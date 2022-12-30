package com.dmakarevich.yellow_collector.sr_receiver.services.processor;

import com.dmakarevich.yellow_collector.sr_receiver.services.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class PushReportProcessorImpl implements PushReportProcessor{

    private final StorageService storageService;

    @Autowired
    public PushReportProcessorImpl(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public void processReportFile(MultipartFile file) {
        storageService.store(file);
    }
}
