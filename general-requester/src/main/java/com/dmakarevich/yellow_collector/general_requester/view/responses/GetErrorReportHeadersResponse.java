package com.dmakarevich.yellow_collector.general_requester.view.responses;

import com.dmakarevich.yellow_collector.general_requester.view.responses.base.ErrorReportHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetErrorReportHeadersResponse {

    private List<ErrorReportHeader> headers;

    public static GetErrorReportHeadersResponse fromErrorReportHeaders(List<ErrorReportHeader> reportList) {
        return new GetErrorReportHeadersResponse(reportList);
    }

}
