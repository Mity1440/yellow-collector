package com.dmakarevich.yellow_collector.sr_receiver.services.storage;

import com.dmakarevich.yellow_collector.sr_receiver.services.storage.exceptions.StorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
public class FileSystemStorageService implements StorageService{

    private final Path location;

    @Autowired
    public FileSystemStorageService(
            @Value("${yellow-collector.sr-receiver.storage.temp:temp/buffer}") String dirLocation) throws IOException {

        this.location = Paths.get(dirLocation);
        init();

    }

    public void init() {
        try {
            Files.createDirectories(location);
            log.info("Initialize location {}", location);
        }
        catch (IOException e) {
            log.error("Error during initializing location {} {}", location, e);
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            Path destinationFile = this
                    .location
                    .resolve(Paths.get(newFileName()))
                    .normalize()
                    .toAbsolutePath();

            if (!destinationFile.getParent().equals(this.location.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,StandardCopyOption.REPLACE_EXISTING);
                log.info("Report {} successfully stored", destinationFile);
            }
        }
        catch (IOException e) {
            log.error("Error during stored report {}", e);
            throw new StorageException("Failed to store file.", e);
        }
    }

    private String newFileName(){
        return UUID.randomUUID().toString()+".zip";
    }

}
