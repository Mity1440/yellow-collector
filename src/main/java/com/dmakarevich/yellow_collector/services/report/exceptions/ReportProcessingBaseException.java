package com.dmakarevich.yellow_collector.services.report.exceptions;

public abstract class ReportProcessingBaseException extends RuntimeException{

    public ReportProcessingBaseException(Exception e) {
        super(e);
    }

}
