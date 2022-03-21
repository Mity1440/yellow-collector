package com.dmakarevich.yellow_collector.services.report.service;

import com.dmakarevich.yellow_collector.configs.StoragePathsProperties;
import com.dmakarevich.yellow_collector.configs.ReportRotationProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ReportRotatorImpl implements ReportRotator{

    private final ReportRotationProperties rotationProperties;
    private final StoragePathsProperties pathsPropertiesProperties;

    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    @Autowired
    public ReportRotatorImpl(ReportRotationProperties rotationProperties,
                             StoragePathsProperties pathsPropertiesProperties) {
        this.rotationProperties = rotationProperties;
        this.pathsPropertiesProperties = pathsPropertiesProperties;
    }

    @Scheduled(cron = "${storage.rotation.purge-reports.cron: 0 0 12 * * *}")
    @Override
    public void purgeReports() throws IOException {

        Path oldReports = Paths.get(pathsPropertiesProperties.getRoot());
        cleanUpDirectory(oldReports, rotationProperties.getOldReports());

        Path errorReports = Paths.get(pathsPropertiesProperties.getError());
        cleanUpDirectory(errorReports, rotationProperties.getErrorReports());

    }

    @Override
    public void rotate(List<Path> successfullyProcessedReports, List<Path> unsuccessfullyProcessedReports) {

        move(successfullyProcessedReports, Path.of(pathsPropertiesProperties.getRoot()));
        move(unsuccessfullyProcessedReports, Path.of(pathsPropertiesProperties.getError()));

    }

    //region Service

    public void move(List<Path> sourceFiles, Path destDir) {

        if (sourceFiles.size() < 0){
            return;
        }

        log.info("start moving {} reports to {}",
                sourceFiles.stream().count(), destDir.getFileName().toString());

        log.debug("moving reports {} }",
                sourceFiles.stream()
                        .map(path -> path.getFileName().toString()).collect(Collectors.joining(", ")));

        sourceFiles.stream()
                .forEach(file->{
                    try {

                        var modifiedTime = formatDateTime(Files.getLastModifiedTime(file));
                        Path subdirectory = Paths.get(destDir.toString(), modifiedTime);
                        Files.createDirectories(subdirectory);
                        Files.move(file, subdirectory.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);

                    } catch (IOException e) {
                        log.error("error during moving report {} {}", file.getFileName().toString(), e);
                    }

                });

    }

    private String formatDateTime(FileTime fileTime) {

        LocalDateTime localDateTime = fileTime
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return localDateTime.format(DATE_FORMATTER);

    }

    private void cleanUpDirectory(Path rootDir, int daysOfFilExistence) throws IOException {

        if (rootDir == null){
            return;
        }

        long fileLifetimeThreshold = System.currentTimeMillis() - (daysOfFilExistence * 24 * 60 * 60 * 1000);

        var fileVisitor = new ReportRotatorFileVisitor(rootDir, fileLifetimeThreshold);
        Files.walkFileTree(rootDir, fileVisitor);

        log.info("cleaned {} files, {} directories", fileVisitor.getCleanedFiles(), fileVisitor.getCleanedDirectories());

    }

    public boolean directoryIsEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)) {
                return !directory.iterator().hasNext();
            }
        }

        return false;
    }

    //endRegion

    @Setter
    @Getter
    private class ReportRotatorFileVisitor extends SimpleFileVisitor<Path> {

        private final long fileLifetimeThreshold;
        private final Path rootDir;
        private int cleanedFiles = 0;
        private int cleanedDirectories = 0;

        private ReportRotatorFileVisitor(Path rootDir, long fileLifetimeThreshold) {
            this.rootDir = rootDir;
            this.fileLifetimeThreshold = fileLifetimeThreshold;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

            var attr = Files.readAttributes(dir, BasicFileAttributes.class);

            if (Files.getLastModifiedTime(dir).to(TimeUnit.MILLISECONDS) <  fileLifetimeThreshold
                    && directoryIsEmpty(dir)
                    && !dir.equals(rootDir)){
                Files.delete(dir);
                cleanedDirectories++;
            }

            return FileVisitResult.CONTINUE;

        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            if (attrs.creationTime().to(TimeUnit.MILLISECONDS) < fileLifetimeThreshold){
                Files.delete(file);
                cleanedFiles++;
            }

            return FileVisitResult.CONTINUE;

        }
    }

}
