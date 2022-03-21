package com.dmakarevich.yellow_collector.services.report.processors;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public interface ReportProcessor {

    void process(BlockingQueue<Path> filesQueue, CountDownLatch latch) throws InterruptedException, IOException;

    default List<Path> getSuccessfullyProcessedReports(){
       throw new UnsupportedOperationException();
    }

    default List<Path> getUnsuccessfullyProcessedReports(){
        throw new UnsupportedOperationException();
    }
}
