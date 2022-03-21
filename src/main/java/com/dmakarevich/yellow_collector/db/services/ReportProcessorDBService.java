package com.dmakarevich.yellow_collector.db.services;

import com.dmakarevich.yellow_collector.services.report.model.file.FileReportErrorInfo;

public interface ReportProcessorDBService {

    void save(FileReportErrorInfo errorInfo);

}
