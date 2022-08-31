package com.dmakarevich.yellow_collector.sr_processor.report.exceptions;

public abstract class ReportProcessingBaseException extends RuntimeException{

    public ReportProcessingBaseException(String message) {
        super(message);
    }

    public ReportProcessingBaseException(Exception e) {
        super(e);
    }

}
