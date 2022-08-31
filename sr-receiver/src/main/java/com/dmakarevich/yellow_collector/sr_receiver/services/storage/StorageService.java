package com.dmakarevich.yellow_collector.sr_receiver.services.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void store(MultipartFile file);
}
