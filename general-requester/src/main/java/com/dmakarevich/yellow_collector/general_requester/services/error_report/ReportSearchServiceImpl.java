package com.dmakarevich.yellow_collector.general_requester.services.error_report;

import com.dmakarevich.yellow_collector.general_requester.db.service.ReportSearchDBService;
import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorReportHeadersRequest;
import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.base.ErrorReportHeader;
import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.GetErrorReportInfoDetailsResponse;
import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.GetErrorReportHeadersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportSearchServiceImpl implements ReportSearchService {

    private final ReportSearchDBService searchEngine;

    @Autowired
    public ReportSearchServiceImpl(ReportSearchDBService searchEngine) {
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
