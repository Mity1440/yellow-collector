package com.dmakarevich.yellow_collector.sr_processor.service.runners;

import java.io.IOException;

public interface ReportProcessorRunner {
    void runProcessing() throws IOException, InterruptedException;
}
