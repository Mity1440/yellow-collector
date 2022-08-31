package com.dmakarevich.yellow_collector.sr_processor.report;

import com.dmakarevich.yellow_collector.sr_processor.report.exceptions.ReportProcessingIOException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;

@Slf4j
public class FileArchiveUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    //region find

    public static Set<Path> find(Path location) throws IOException {
        return find(location, Integer.MAX_VALUE);
    }

    public static Set<Path> find(Path location, int maximumFilesCount) throws IOException {

        var zipFiles = listAllZipFilesFromDirectory(location, maximumFilesCount);
        if (zipFiles.size() > 0){
            log.info("found {} files", zipFiles.stream().count());
        }
        log.debug("found {} files: {}", zipFiles.stream().count(),
                zipFiles.stream()
                        .map(path -> path.getFileName().toString())
                        .collect(Collectors.joining(", ")));

        return zipFiles;

    }

    private static Set<Path> listAllZipFilesFromDirectory(Path location, int maximumFilesCount) throws IOException {

        Set<Path> fileList = new HashSet<>();
        Files.walkFileTree(location, new SimpleFileVisitor<Path>() {
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

    //endregion

    //region move

    public static void move(List<Path> sourceFiles, Path destDir) {

        if (sourceFiles.size() < 0){
            return;
        }

        log.info("start moving {} reports to {}",
                sourceFiles.stream().count(), destDir.getFileName().toString());

        log.debug("moving reports {} }",
                sourceFiles.stream()
                        .map(path -> path.getFileName().toString()).collect(Collectors.joining(", ")));

        for (var file: sourceFiles){

            try {
                Path subdirectory = Paths.get(destDir.toString(),
                        formatFileModifiedTime(Files.getLastModifiedTime(file)));
                Files.createDirectories(subdirectory);
                Files.move(file,
                        subdirectory.resolve(file.getFileName()),
                        StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                log.error("error during moving report {} {}", file.getFileName().toString(), e);
            }

        }

    }

    private static String formatFileModifiedTime(FileTime fileTime) {

        LocalDateTime localDateTime = fileTime
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return localDateTime.format(DATE_FORMATTER);

    }

    //endregion

    //region cleanUp

    public static void cleanUpDirectory(Path rootDir, int daysOfFileExistence) throws IOException {

        if (rootDir == null){
            return;
        }

        long fileLifetimeThreshold = System.currentTimeMillis() - (daysOfFileExistence
                                                                   * 24 * 60 * 60 // секунд в сутках
                                                                   * 1000); // Множитель для перевода в милисекунды

        var fileVisitor = new CleanUpFileVisitor(rootDir, fileLifetimeThreshold);
        Files.walkFileTree(rootDir, fileVisitor);

        log.info("removed {} files, {} directories",
                 fileVisitor.getCleanedFilesCount(),
                 fileVisitor.getCleanedDirectoriesCount());

    }

    private static boolean directoryIsEmpty(Path path) throws IOException {

        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)) {
                return !directory.iterator().hasNext();
            }
        }

        return false;

    }

    @Setter
    @Getter
    private static class CleanUpFileVisitor extends SimpleFileVisitor<Path> {

        private final long fileLifeTimeThreshold;
        private final Path rootDir;
        private int CleanedFilesCount = 0;
        private int CleanedDirectoriesCount = 0;

        private CleanUpFileVisitor(Path rootDir, long fileLifeTimeThreshold) {
            this.rootDir = rootDir;
            this.fileLifeTimeThreshold = fileLifeTimeThreshold;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            
            if (Files.getLastModifiedTime(dir).to(TimeUnit.MILLISECONDS) <  fileLifeTimeThreshold
                    && directoryIsEmpty(dir)
                    && !dir.equals(rootDir)){
                Files.delete(dir);
                CleanedDirectoriesCount++;
            }

            return FileVisitResult.CONTINUE;

        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            if (attrs.creationTime().to(TimeUnit.MILLISECONDS) < fileLifeTimeThreshold){
                Files.delete(file);
                CleanedFilesCount++;
            }

            return FileVisitResult.CONTINUE;

        }
    }

    //endregion

    //region service

    public static Optional<String> getFileExtensionByFullName(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static String getNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }

    public static String getJsonContentFromZipFile(ZipFile zipFile) {

        String result = "";

        var entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            var entry = entries.nextElement();
            if (!entry.isDirectory()
                    && getFileExtensionByFullName(entry.getName())
                    .orElse("")
                    .equalsIgnoreCase("json")) {

                try (InputStream stream = zipFile.getInputStream(entry)) {
                    result = new String(stream.readAllBytes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }

        return result;

    }

    //endregion
}
