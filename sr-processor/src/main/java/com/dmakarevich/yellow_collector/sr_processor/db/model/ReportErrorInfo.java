package com.dmakarevich.yellow_collector.sr_processor.db.model;

import com.dmakarevich.yellow_collector.sr_processor.db.model.details.ErrorInfo;
import com.dmakarevich.yellow_collector.sr_processor.db.model.details.ErrorInfoDetail;
import com.dmakarevich.yellow_collector.sr_processor.db.model.details.ErrorInfoStackDetail;
import com.dmakarevich.yellow_collector.sr_processor.db.model.header.ReportHeader;
import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileReportErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.dmakarevich.yellow_collector.sr_processor.db.conventers.ReportConverter.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportErrorInfo {

    private ReportHeader header;
    private ErrorInfo errorInfo;
    private List<ErrorInfoDetail> errorDetails;
    private List<ErrorInfoStackDetail> errorStack;

    public static ReportErrorInfo fromFileErrorInfo(FileReportErrorInfo fileErrorInfo) {

        var reportErrorInfo =
                new ReportErrorInfo(fromFileReportErrorInfoToReportHeader(fileErrorInfo),
                                    fromFileReportErrorInfoToErrorInfo(fileErrorInfo),
                                    fromFileReportErrorToErrorInfoDetails(fileErrorInfo),
                                    fromFileReportErrorToErrorStack(fileErrorInfo));

        reportErrorInfo.fillLastLineOfTheErrorContext();
        reportErrorInfo.fillUserDescription();
        reportErrorInfo.fillClientAndServerStackHash();
        reportErrorInfo.linkErrorDetailsWithErrorInfo();
        reportErrorInfo.linkErrorStackWithErrorInfo();

        return reportErrorInfo;

    }

    private void fillClientAndServerStackHash() {

        if (errorInfo == null || errorInfo.getSystemErrorInfo() == null){
            return;
        }

        header.setClientStackHash(errorInfo.getSystemErrorInfo().getClientStackHash());
        header.setServerStackHash(errorInfo.getSystemErrorInfo().getServerStackHash());

    }

    private void fillUserDescription() {

        if (errorInfo == null ){
            return;
        }

        header.setUserDescription(errorInfo.getUserDescription());

    }

    private void fillLastLineOfTheErrorContext() {

        if (errorStack == null || errorStack.size() == 0){
            return;
        }

        var lastElement = errorStack.get(errorStack.size() - 1);
        header.setLastLineOfTheErrorContext(String.format("%s. %s. %s",
                                                          lastElement.moduleName,
                                                          lastElement.lineNumber,
                                                          lastElement.sourceModuleText));

    }

    private void linkErrorStackWithErrorInfo() {
        errorStack.forEach(errorStackElement -> errorStackElement.setErrorInfoId(errorInfo.getId()));
        if (errorInfo.getApplicationErrorInfo() != null &&
                errorInfo.getApplicationErrorInfo().getStack() != null){
            var stack = errorInfo.getApplicationErrorInfo().getStack();
            errorStack.forEach(errorStackElement -> stack.add(errorStackElement.getId()));
        }
    }

    private void linkErrorDetailsWithErrorInfo() {
        errorDetails.forEach(errorInfoDetail -> errorInfoDetail.setErrorInfoId(errorInfo.getId()));
        if (errorInfo.getApplicationErrorInfo() != null &&
                errorInfo.getApplicationErrorInfo().getErrors() != null){
            var errors = errorInfo.getApplicationErrorInfo().getErrors();
            errorDetails.forEach(errorInfoDetail -> errors.add(errorInfoDetail.getId()));
        }
    }

}
