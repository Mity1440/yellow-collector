package com.dmakarevich.yellow_collector.services.report.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ReportRotator {

    public void purgeReports() throws IOException;
    public void rotate(List<Path> successfullyProcessedReports, List<Path> unsuccessfullyProcessedReports);

}
