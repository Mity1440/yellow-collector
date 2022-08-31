package com.dmakarevich.yellow_collector.general_requester.services;

import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorReportHeadersRequest;
import com.dmakarevich.yellow_collector.general_requester.view.responses.GetErrorReportInfoDetailsResponse;
import com.dmakarevich.yellow_collector.general_requester.view.responses.GetErrorReportHeadersResponse;

public interface ErrorSearchService {

    GetErrorReportHeadersResponse getErrorHeaders(GetErrorReportHeadersRequest request);

    GetErrorReportInfoDetailsResponse getErrorDetails(String id);

}
