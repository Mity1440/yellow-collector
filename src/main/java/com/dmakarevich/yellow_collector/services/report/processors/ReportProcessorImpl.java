package com.dmakarevich.yellow_collector.services.report.processors;

import com.dmakarevich.yellow_collector.db.services.ReportProcessorDBService;
import com.dmakarevich.yellow_collector.services.report.exceptions.ReportProcessingIOException;
import com.dmakarevich.yellow_collector.services.report.exceptions.ReportProcessingParseException;
import com.dmakarevich.yellow_collector.services.report.model.file.FileReportErrorInfo;
import com.dmakarevich.yellow_collector.services.report.parsers.ReportParser;

import com.mongodb.MongoSocketException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.zip.ZipFile;

@Component
@Slf4j
public class ReportProcessorImpl implements ReportProcessor{

    private final ReportParser parser;
    private final ReportProcessorDBService reportService;
    private final ConcurrentLinkedQueue<Path> successfullyProcessedReports;
    private final ConcurrentLinkedQueue<Path> unsuccessfullyProcessedReports;

    @Autowired
    public ReportProcessorImpl(ReportParser parser, ReportProcessorDBService reportService) {
        this.parser = parser;
        this.reportService = reportService;
        this.successfullyProcessedReports = new ConcurrentLinkedQueue<>();
        this.unsuccessfullyProcessedReports = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void process(BlockingQueue<Path> filesQueue, CountDownLatch latch) throws InterruptedException {

        while (!Thread.currentThread().isInterrupted()){

            Path pathToReport = filesQueue.poll(1, TimeUnit.SECONDS);
            if (pathToReport == null){
                break; // Процессор работает до тех пор пока в очереди есть элементы
            }

            processReport(pathToReport);
            latch.countDown(); // Всегда снимаем защелку

        }

    }

    @Override
    public List<Path> getSuccessfullyProcessedReports(){

        var result = Collections.unmodifiableList(this.successfullyProcessedReports.stream().toList());
        this.successfullyProcessedReports.clear();

        return result;

    }

    @Override
    public List<Path> getUnsuccessfullyProcessedReports(){

        var result = Collections.unmodifiableList(this.unsuccessfullyProcessedReports.stream().toList());
        this.unsuccessfullyProcessedReports.clear();

        return result;

    }

    //region Service

    private void processReport(Path reportFile){
        wrapException(()-> reportService.save(getReportErrorInfoFromFileReport(reportFile)), reportFile);
    }

    private void wrapException(Runnable task, Path reportFile) {

        try {
            task.run();
            successfullyProcessedReports.add(reportFile);
            log.info(String.format("Report %s successfully processed", reportFile.getFileName().toString()));
        // С таким исключеннием считаем отчет, неуспешно обработанным. если возникла ошибка парсинга
        // повторять операцию нет никакого смысла
        } catch (ReportProcessingParseException | ReportProcessingIOException ex) {
            unsuccessfullyProcessedReports.add(reportFile);
            log.error("Error during processing report {} {}. Problem report will be moved to the catalog of erroneous reports",
                    reportFile.getFileName().toString(), ex);
        // Такое исключение может говорить о временной недоступности БД, отчет считать неуспешно обработанным не будем.
        // В следующий раз операция по обработке будет повторена
        } catch (MongoSocketException ex) {
            log.error("Error during processing report {} {}. A re-processing attempt will be made later",
                    reportFile.getFileName().toString(), ex);
        }catch (Exception ex){
            unsuccessfullyProcessedReports.add(reportFile);
            log.error("Error during processing report {} {}. Problem report will be moved to the catalog of erroneous reports",
                    reportFile.getFileName().toString(), ex);
        }

    }

    //region WorkWithJson

    private FileReportErrorInfo getReportErrorInfoFromFileReport(Path reportFile) {

        FileReportErrorInfo errorInfo = null;
        try (ZipFile reportZip = new ZipFile(reportFile.toFile())){

            errorInfo = parser.parse(getJsonReportString(reportZip));
            if (errorInfo.getId() == null) {
                errorInfo.setId(
                        getNameWithoutExtension(
                                reportFile.getFileName().toString()));
            }

        }catch (IOException | ReportProcessingIOException ex){
            throw new ReportProcessingIOException(ex);
        } catch (Exception ex) { // остальные исключения относим к ошибкам парсинга
            throw new ReportProcessingParseException(ex);
        }

        return errorInfo;

    }

    private String getJsonReportString(ZipFile zipFile) {

        String result = null;

        var entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            var entry = entries.nextElement();
            if (!entry.isDirectory()
                    && getFileExtensionByFullName(entry.getName())
                    .orElse("")
                    .equalsIgnoreCase("json")) {

                try (InputStream stream = zipFile.getInputStream(entry)) {
                    result = new String(stream.readAllBytes());
                } catch (IOException ex) {
                    throw new ReportProcessingIOException(ex);
                }

            }
        }

        return result;

    }

    public Optional<String> getFileExtensionByFullName(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public String getNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }

    //endregion

    //endregion

}
