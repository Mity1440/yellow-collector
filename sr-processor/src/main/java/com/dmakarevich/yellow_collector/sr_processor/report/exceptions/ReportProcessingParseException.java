package com.dmakarevich.yellow_collector.sr_processor.report.exceptions;

public class ReportProcessingParseException extends ReportProcessingBaseException{

    public ReportProcessingParseException(String message) {
        super(message);
    }

    public ReportProcessingParseException(Exception e) {
        super(e);
    }

}
