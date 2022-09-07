package com.dmakarevich.yellow_collector.general_requester.services.errors;

import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorReportHeadersRequest;
import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.GetErrorReportInfoDetailsResponse;
import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.GetErrorReportHeadersResponse;

public interface ErrorSearchService {

    GetErrorReportHeadersResponse getErrorHeaders(GetErrorReportHeadersRequest request);

    GetErrorReportInfoDetailsResponse getErrorDetails(String id);

}
