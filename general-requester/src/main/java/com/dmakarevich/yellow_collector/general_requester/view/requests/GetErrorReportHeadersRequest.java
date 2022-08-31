package com.dmakarevich.yellow_collector.general_requester.view.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetErrorReportHeadersRequest {

    private Date from;
    private Date to;

}
