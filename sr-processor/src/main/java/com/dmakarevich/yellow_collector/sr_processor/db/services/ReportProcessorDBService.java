package com.dmakarevich.yellow_collector.sr_processor.db.services;

import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileReportErrorInfo;

public interface ReportProcessorDBService {

    void save(FileReportErrorInfo errorInfo);

}
