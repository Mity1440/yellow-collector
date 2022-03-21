package com.dmakarevich.yellow_collector.services.report.parsers;

import com.dmakarevich.yellow_collector.services.report.model.file.FileReportErrorInfo;

public interface ReportParser {

    FileReportErrorInfo parse(String jsonString);

}
