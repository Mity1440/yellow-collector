package com.dmakarevich.yellow_collector.sr_receiver.services;

import com.dmakarevich.yellow_collector.sr_receiver.config.StorageProperties;
import com.dmakarevich.yellow_collector.sr_receiver.services.storage.exceptions.StorageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
@Slf4j
public class MainStartupRunner implements ApplicationRunner {

    private final StorageProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initTempStorageLocation();
    }

    //Service

    private void initTempStorageLocation() {
        var location = Paths.get(properties.getTemp());
        try {
            Files.createDirectories(location);
            log.info("Initialize location {}", location);
        }
        catch (IOException e) {
            log.error("Error during initializing location {} {}", location, e);
            throw new StorageException("Could not initialize storage", e);
        }
    }

    //

}
