package com.dmakarevich.yellow_collector.services.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();

    void store(MultipartFile file);

}
