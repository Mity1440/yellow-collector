package com.dmakarevich.yellow_collector.sr_processor.report.processors;

import com.dmakarevich.yellow_collector.sr_processor.dao.services.ReportProcessorDBService;
import com.dmakarevich.yellow_collector.sr_processor.report.exceptions.*;
import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileReportErrorInfo;
import com.dmakarevich.yellow_collector.sr_processor.report.ReportParser;
import com.dmakarevich.yellow_collector.sr_processor.report.ProcessReportResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipFile;

import static com.dmakarevich.yellow_collector.sr_processor.report.FileArchiveUtils.*;

@Component
@Slf4j
public class ReportProcessorImpl implements ReportProcessor{

    private final ReportProcessorDBService reportService;

    @Autowired
    public ReportProcessorImpl(ReportProcessorDBService reportService) {
        this.reportService = reportService;
    }

    @Override
    public void process(BlockingQueue<Path> filesQueue,
                        CountDownLatch latch,
                        ProcessReportResults processReportResults) throws InterruptedException {

        while (!Thread.currentThread().isInterrupted()){

            Path pathToReport = filesQueue.poll(1, TimeUnit.SECONDS);
            if (pathToReport == null){
                break; // Процессор работает до тех пор пока в очереди есть элементы
            }

            processReport(pathToReport, processReportResults);
            latch.countDown(); // Всегда снимаем защелку

        }

    }

    //region Service

    private void processReport(Path reportFile, ProcessReportResults processReportResults){

        try {
            reportService.save(getReportErrorInfoFromFileReport(reportFile));
            processReportResults.addSuccessfullyProcessedReport(reportFile);
            log.info("Report {} successfully processed", reportFile.getFileName().toString());
        } catch (Exception ex) {
            processReportResults.addUnsuccessfullyProcessedReport(reportFile);
            log.error("Error during processing report {} {}. Problem report will be moved to the catalog of erroneous reports",
                    reportFile.getFileName().toString(), ex);
        }

    }

    //region WorkWithJson

    private FileReportErrorInfo getReportErrorInfoFromFileReport(Path reportFile) {

        FileReportErrorInfo errorInfo = null;
        try (ZipFile reportZip = new ZipFile(reportFile.toFile())){

            errorInfo = ReportParser.parse(getJsonContentFromZipFile(reportZip));
            if (errorInfo.getId() == null) {
                errorInfo.setId(getErrorInfoId(reportFile));
            }

        }catch (IOException | ReportProcessingIOException ex){
            throw new ReportProcessingIOException(ex);
        } catch (Exception ex) { // остальные исключения относим к ошибкам парсинга
            throw new ReportProcessingParseException(ex);
        }

        return errorInfo;

    }

    private String getErrorInfoId(Path reportFile) {
        return getNameWithoutExtension(reportFile.getFileName().toString());
    }

    //endregion

    //endregion

}
