package com.dmakarevich.yellow_collector.services.report.runners;

import com.dmakarevich.yellow_collector.configs.ReportRunnersProperties;
import com.dmakarevich.yellow_collector.services.report.processors.ReportProcessor;
import com.dmakarevich.yellow_collector.services.report.service.ReportArchiveFinder;
import com.dmakarevich.yellow_collector.services.report.service.ReportRotator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.*;

@Component
public class ReportProcessorRunnerImpl implements ReportProcessorRunner{

    private final ReportArchiveFinder finder;
    private final ExecutorService workerPool;
    private final ReportRunnersProperties runnersProperties;
    private final BlockingQueue<Path> filesProcessingQueue;
    private final ReportProcessor processor;
    private final ReportRotator reportRotator;

    @Autowired
    public ReportProcessorRunnerImpl(ReportArchiveFinder finder,
                                     ReportProcessor processor,
                                     ReportRotator reportRotator,
                                     ReportRunnersProperties runnersProperties) {
        this.finder = finder;
        this.processor = processor;
        this.reportRotator = reportRotator;
        this.runnersProperties = runnersProperties;

        this.workerPool = Executors.newFixedThreadPool(this.runnersProperties.getWorkerPoolSize());
        this.filesProcessingQueue
                = new ArrayBlockingQueue(this.runnersProperties.getMaximumFileProcessingQueueSizePerIteration());

    }

    @Scheduled(initialDelayString = "${initial-delay.in.milliseconds:10000}",
               fixedDelayString = "${runners.fixed-delay.in.milliseconds:2000}")
    public void startProcessing() throws IOException, InterruptedException {

        fillFilesProcessingQueue();
        performFilesQueueProcess(getProcessorLatch());
        performPostProcessingActions();

    }

    //region Service

    private void fillFilesProcessingQueue() throws IOException {

        var reportFiles = finder.find(runnersProperties.getMaximumFileProcessingQueueSizePerIteration());
        reportFiles.stream().forEach(filesProcessingQueue::offer);

    }

    private CountDownLatch getProcessorLatch() {
        return new CountDownLatch(filesProcessingQueue.size());
    }

    private void performFilesQueueProcess(CountDownLatch processorLath) throws InterruptedException {

        for (var idx = 0; idx < 5; idx++){
            workerPool.submit(()->{
                try {
                    processor.process(filesProcessingQueue, processorLath);
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        processorLath.await(5, TimeUnit.MINUTES);

    }

    private void performPostProcessingActions() {

        var successfullyProcessedReports = processor.getSuccessfullyProcessedReports();
        var unsuccessfullyProcessedReports = processor.getUnsuccessfullyProcessedReports();

        if (successfullyProcessedReports.size() > 0 || unsuccessfullyProcessedReports.size() > 0) {
            reportRotator.rotate(successfullyProcessedReports, unsuccessfullyProcessedReports);
        }

    }

    //endregion

}
