package com.dmakarevich.yellow_collector.sr_processor.report.processors;

import com.dmakarevich.yellow_collector.sr_processor.configs.ReportProcessorProperties;
import com.dmakarevich.yellow_collector.sr_processor.db.services.ReportProcessorDBService;
import com.dmakarevich.yellow_collector.sr_processor.report.ProcessReportResults;
;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.CountDownLatch;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ReportProcessorImplTest {

    private final int DEFAULT_WORKER_POOL_SIZE = 1;
    private final int DEFAULT_SIZE_OF_REPORT_PROCESSING_QUEUE = 5;
    private final String DEFAULT_FILE_NAME = "temp-error-report.zip";

    private ReportProcessorDBService service;
    private ReportProcessor processor;
    private ReportProcessorProperties properties;

    @BeforeEach
    void setUp() {

        properties = mock(ReportProcessorProperties.class);
        service = mock(ReportProcessorDBService.class);
        processor = new ReportProcessorImpl(service);

        given(properties.getRunnersWorkerPoolSize()).willReturn(DEFAULT_WORKER_POOL_SIZE);
        given(properties.getSizeOfReportProcessingQueue()).willReturn(DEFAULT_SIZE_OF_REPORT_PROCESSING_QUEUE);

    }

    @Test
    @DisplayName("Если во время обработки отчета возникло исключение, то отчет должен быть помещен в список 'Неуспешно обработанных'")
    void processUnsuccessful() throws IOException, InterruptedException {

        var queue = new ArrayBlockingQueue<Path>(5);
        var latch  = new CountDownLatch(queue.size());
        var results = new ProcessReportResults();

        var path = createEmptyZipFile();
        queue.offer(path);

        doThrow(RuntimeException.class).when(service).save(any());

        processor.process(queue, latch, results);

        var condition =
                new Condition<ProcessReportResults>(
                        m -> m.getListOfUnsuccessfullyProcessedReports().size() == 1,
                "Ожидали, что список 'Неуспешно обработанных отчет' содержит один элемент, но это не так ");

        Assertions.assertThat(results)
                .isInstanceOf(ProcessReportResults.class)
                .has(condition);

        Files.deleteIfExists(path);

    }

    @Test
    @DisplayName("Если пытаемся обработать файл архив, соежржимое которого не соотвествует формату" +
            ", то отчет должен быть помещен в список 'Неуспешно обработанных'")
    void processSuccessful() throws IOException, InterruptedException {

        var queue = new ArrayBlockingQueue<Path>(5);
        var latch  = new CountDownLatch(queue.size());
        var results = new ProcessReportResults();

        var path = createEmptyZipFile();
        queue.offer(path);

        processor.process(queue, latch, results);

        var condition =
                new Condition<ProcessReportResults>(
                        m -> m.getListOfUnsuccessfullyProcessedReports().size() == 1,
                        "Ожидали, что список 'Неуспешно обработанных отчет' содержит один элемент, но это не так ");

        Assertions.assertThat(results)
                .isInstanceOf(ProcessReportResults.class)
                .has(condition);

        Files.deleteIfExists(path);

    }

    private Path createEmptyZipFile() throws IOException {

        var tempFile = Files.createTempFile(DEFAULT_FILE_NAME, ".zip");
        try (var zos = new ZipOutputStream(new FileOutputStream(tempFile.toFile()))) {

            ZipEntry zipEntry = new ZipEntry("report.json");
            zos.putNextEntry(zipEntry);

            zos.write("".getBytes());
            zos.closeEntry();

        }

        return tempFile;

    }
}