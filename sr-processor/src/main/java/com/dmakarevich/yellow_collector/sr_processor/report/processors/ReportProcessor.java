package com.dmakarevich.yellow_collector.sr_processor.report.processors;

import com.dmakarevich.yellow_collector.sr_processor.report.ProcessReportResults;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public interface ReportProcessor {

    void process(BlockingQueue<Path> filesQueue,
                 CountDownLatch latch,
                 ProcessReportResults processingResults) throws InterruptedException, IOException;

}
