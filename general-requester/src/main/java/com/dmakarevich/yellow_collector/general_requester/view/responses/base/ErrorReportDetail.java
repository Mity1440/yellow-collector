package com.dmakarevich.yellow_collector.general_requester.view.responses.base;

import com.dmakarevich.yellow_collector.general_requester.db.model.ReportInfoDetail;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ErrorReportDetail {

    private ErrorReportHeader header;
    private ErrorReportInfo errorInfo;
    private List<ErrorReportInfoDetail> errorDetails;
    private List<ErrorReportInfoStackDetail> errorStack;

    public static ErrorReportDetail fromModel(ReportInfoDetail model){
        return new ErrorReportDetail(ErrorReportHeader.fromModel(model.getHeader())
                      , ErrorReportInfo.fromModel(model.getErrorInfo())
                      , model.getErrorDetails().stream().map(ErrorReportInfoDetail::fromModel).collect(Collectors.toList())
                      , model.getErrorStack().stream().map(ErrorReportInfoStackDetail::fromModel).collect(Collectors.toList()));
    }

}
