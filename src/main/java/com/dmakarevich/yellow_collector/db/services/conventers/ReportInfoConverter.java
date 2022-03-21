package com.dmakarevich.yellow_collector.db.services.conventers;

import com.dmakarevich.yellow_collector.db.model.info.ErrorInfo;
import com.dmakarevich.yellow_collector.db.model.info.ErrorInfoDetail;
import com.dmakarevich.yellow_collector.db.model.info.ErrorInfoStackDetail;
import com.dmakarevich.yellow_collector.db.model.main.BasicErrorReportInfo;

import java.util.List;

public interface ReportInfoConverter<T> {

    BasicErrorReportInfo fromFileReportErrorInfoToBasicErrorReportInfo(T fileReportInfo);

    ErrorInfo fromFileReportErrorInfoToErrorInfo(T fileReportInfo);

    List<ErrorInfoDetail> fromFileReportErrorToErrorInfoDetails(T fileReportInfo);

    List<ErrorInfoStackDetail> fromFileReportErrorToErrorStack(T fileReportInfo);

}
