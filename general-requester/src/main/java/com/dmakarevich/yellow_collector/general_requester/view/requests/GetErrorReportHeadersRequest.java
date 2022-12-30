package com.dmakarevich.yellow_collector.general_requester.view.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetErrorReportHeadersRequest {

    @NotNull
    private Date from;
    @NotNull
    private Date to;
    private boolean includeDeletedMarkReports;

}
