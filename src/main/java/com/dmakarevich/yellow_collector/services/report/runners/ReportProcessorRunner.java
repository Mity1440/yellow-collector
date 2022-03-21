package com.dmakarevich.yellow_collector.services.report.runners;

import java.io.IOException;

public interface ReportProcessorRunner {

    void startProcessing() throws IOException, InterruptedException;

}
