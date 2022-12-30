package com.dmakarevich.yellow_collector.sr_processor.service.runners;

import com.dmakarevich.yellow_collector.sr_processor.config.ReportProcessorProperties;
import com.dmakarevich.yellow_collector.sr_processor.report.ProcessReportResults;
import com.dmakarevich.yellow_collector.sr_processor.report.processors.ReportProcessor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.*;

import static com.dmakarevich.yellow_collector.sr_processor.report.FileArchiveUtils.*;

@Service
@Slf4j
public class ReportProcessorRunnerImpl implements ReportProcessorRunner{

    private final ExecutorService workerPool;
    private final BlockingQueue<Path> reportProcessingQueue;
    private final ReportProcessor processor;
    private final ReportProcessorProperties properties;

    public ReportProcessorRunnerImpl(ReportProcessor processor, ReportProcessorProperties properties) {

        this.processor = processor;
        this.properties = properties;
        this.workerPool = Executors.newFixedThreadPool(properties.getRunnersWorkerPoolSize());
        this.reportProcessingQueue = new ArrayBlockingQueue<Path>(properties.getSizeOfReportProcessingQueue());

    }

    @Override
    @Scheduled(initialDelayString = "${yellow-collector.runners.initial-delay-in-milliseconds:10000}",
               fixedDelayString = "${yellow-collector.runners.fixed-delay-in-milliseconds:2000}")
    public void runProcessing() throws IOException, InterruptedException {

        fillReportQueue();

        var processingResults = new ProcessReportResults();
        processReportQueue(processingResults, getProcessorLatch());

        performPostProcessingActions(processingResults);

    }

    //region Service

    private void fillReportQueue() throws IOException {

        // ищем файлов не более чем может прожевать очередь на одной итерации обработки
        var reportFiles = find(properties.getReportTempStorageLocation(),
                                         properties.getSizeOfReportProcessingQueue());

        for (var reportFile: reportFiles){
            if (!reportProcessingQueue.offer(reportFile)){
                log.error("{} can not be placed in processing queue", reportFile);
            }
        }

    }

    private CountDownLatch getProcessorLatch() {
        return new CountDownLatch(reportProcessingQueue.size());
    }

    private void processReportQueue(ProcessReportResults processingResults,
                                    CountDownLatch processorLath) throws InterruptedException {

        for (var idx = 0; idx < properties.getRunnersWorkerPoolSize(); idx++){
            workerPool.submit(()->{
                try {
                    processor.process(reportProcessingQueue, processorLath, processingResults);
                } catch (Exception e) {
                    log.error("Error during processing reports {}", e);
                }
            });
        }

        processorLath.await(properties.getTimeoutOnReportProcessingIteration(), TimeUnit.MINUTES);

    }

    private void performPostProcessingActions(ProcessReportResults processingResults) {

        if (processingResults.existSuccessfullyProcessedReports()){
            move(processingResults.getListOfSuccessfullyProcessedReports(), properties.getReportStorageLocation());
        }

        if (processingResults.existUnsuccessfullyProcessedReports()){
            move(processingResults.getListOfUnsuccessfullyProcessedReports(), properties.getReportErrorStorageLocation());
        }

    }

    //endregion

}
