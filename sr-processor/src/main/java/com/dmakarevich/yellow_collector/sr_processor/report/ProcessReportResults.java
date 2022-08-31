package com.dmakarevich.yellow_collector.sr_processor.report;

import lombok.Data;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Data
public class ProcessReportResults {

    private final ConcurrentLinkedQueue<Path> successfullyProcessedReports;
    private final ConcurrentLinkedQueue<Path> unsuccessfullyProcessedReports;

    public ProcessReportResults() {
        this.successfullyProcessedReports = new ConcurrentLinkedQueue<>();
        this.unsuccessfullyProcessedReports = new ConcurrentLinkedQueue<>();
    }

    public boolean existSuccessfullyProcessedReports(){
        return this.successfullyProcessedReports.size() > 0;
    }

    public boolean existUnsuccessfullyProcessedReports(){
        return this.unsuccessfullyProcessedReports.size() > 0;
    }

    public List<Path> getListOfSuccessfullyProcessedReports(){
        return Collections
                .unmodifiableList(this.successfullyProcessedReports
                        .stream().collect(Collectors.toList()));
    }

    public List<Path> getListOfUnsuccessfullyProcessedReports(){
        return Collections
                .unmodifiableList(this.unsuccessfullyProcessedReports
                        .stream().collect(Collectors.toList()));
    }

    public void addSuccessfullyProcessedReport(Path report){
        this.successfullyProcessedReports.add(report);
    }

    public void addUnsuccessfullyProcessedReport(Path report){
        this.unsuccessfullyProcessedReports.add(report);
    }

}
