package com.dmakarevich.yellow_collector.general_requester.services;

import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorHeadersRequest;
import com.dmakarevich.yellow_collector.general_requester.view.responses.GetErrorHeadersResponse;

import java.util.List;

public interface ErrorSearchService {

    List<GetErrorHeadersResponse> getErrorHeaders(GetErrorHeadersRequest request);

}
