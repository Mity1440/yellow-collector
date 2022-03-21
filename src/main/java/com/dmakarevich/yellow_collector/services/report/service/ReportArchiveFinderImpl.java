package com.dmakarevich.yellow_collector.services.report.service;

import com.dmakarevich.yellow_collector.configs.StoragePathsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ReportArchiveFinderImpl implements ReportArchiveFinder {

    private final StoragePathsProperties storagePathsProperties;

    @Autowired
    public ReportArchiveFinderImpl(StoragePathsProperties storagePathsProperties) {
        this.storagePathsProperties = storagePathsProperties;
    }

    @Autowired
    public Set<Path> find() throws IOException {
        return find(Integer.MAX_VALUE);
    }

    @Override
    public Set<Path> find(int maximumFilesCount) throws IOException {

        var zipFiles = listAllZipFilesFromDirectory(storagePathsProperties.getTemp(), maximumFilesCount);
        if (zipFiles.size() > 0){
            log.info("found {} files", zipFiles.stream().count());
        }
        log.debug("found {} files: {}", zipFiles.stream().count(),
                zipFiles.stream()
                        .map(path -> path.getFileName().toString())
                        .collect(Collectors.joining(", ")));

        return zipFiles;

    }

    //region Service

    public Set<Path> listAllZipFilesFromDirectory(String dir, int maximumFilesCount) throws IOException {

        Set<Path> fileList = new HashSet<>();
        Files.walkFileTree(Paths.get(dir), new SimpleFileVisitor<Path>() {
            int fileCounter = 0;
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                if (fileCounter > maximumFilesCount){
                    return FileVisitResult.TERMINATE;
                }

                if (!Files.isDirectory(file)
                        && getFileExtensionByFullName(file.getFileName().toString())
                        .orElse("")
                        .equalsIgnoreCase("zip")) {
                    fileList.add(file);
                    fileCounter++;
                }
                return FileVisitResult.CONTINUE;
            }

        });

        return fileList;
    }

    public Optional<String> getFileExtensionByFullName(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    //endregion

}
