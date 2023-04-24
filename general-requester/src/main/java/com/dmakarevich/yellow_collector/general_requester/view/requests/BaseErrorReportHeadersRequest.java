package com.dmakarevich.yellow_collector.general_requester.view.requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@RequiredArgsConstructor
public abstract class BaseErrorReportHeadersRequest {

    @NotNull
    private Date from;
    @NotNull
    private Date to;
    private boolean includeDeletedMarkReports;

}
