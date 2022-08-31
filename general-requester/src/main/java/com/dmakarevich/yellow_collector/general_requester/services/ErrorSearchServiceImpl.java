package com.dmakarevich.yellow_collector.general_requester.services;

import com.dmakarevich.yellow_collector.general_requester.db.service.ErrorSearchDBService;
import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorReportHeadersRequest;
import com.dmakarevich.yellow_collector.general_requester.view.responses.base.ErrorReportHeader;
import com.dmakarevich.yellow_collector.general_requester.view.responses.GetErrorReportInfoDetailsResponse;
import com.dmakarevich.yellow_collector.general_requester.view.responses.GetErrorReportHeadersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class ErrorSearchServiceImpl implements ErrorSearchService{

    private final ErrorSearchDBService searchEngine;

    @Autowired
    public ErrorSearchServiceImpl(ErrorSearchDBService searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public GetErrorReportHeadersResponse getErrorHeaders(GetErrorReportHeadersRequest request) {
        return  GetErrorReportHeadersResponse
                     .fromErrorReportHeaders(
                           searchEngine.getReportHeaders(request).stream().map(ErrorReportHeader::fromModel)
                             .collect(Collectors.toList()));

    }

    @Override
    public GetErrorReportInfoDetailsResponse getErrorDetails(String id) {
        return GetErrorReportInfoDetailsResponse.fromErrorDetails(searchEngine.getErrorDetails(id));
    }

}
