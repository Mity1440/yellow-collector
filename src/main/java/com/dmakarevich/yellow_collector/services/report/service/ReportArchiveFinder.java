package com.dmakarevich.yellow_collector.services.report.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public interface ReportArchiveFinder {

    Set<Path> find() throws IOException;

    Set<Path> find(int maximumFilesCount) throws IOException;

}
