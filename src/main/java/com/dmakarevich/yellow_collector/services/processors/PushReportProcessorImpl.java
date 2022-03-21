package com.dmakarevich.yellow_collector.services.processors;

import com.dmakarevich.yellow_collector.services.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
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
