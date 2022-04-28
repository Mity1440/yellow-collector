package com.dmakarevich.yellow_collector.general_requester.services;

import com.dmakarevich.yellow_collector.general_requester.dao.model.header.ReportHeader;
import com.dmakarevich.yellow_collector.general_requester.dao.service.ErrorSearchDBService;
import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorHeadersRequest;
import com.dmakarevich.yellow_collector.general_requester.view.responses.GetErrorHeadersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ErrorSearchServiceImpl implements ErrorSearchService{

    private final ErrorSearchDBService searchEngine;

    public ErrorSearchServiceImpl(ErrorSearchDBService searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public List<GetErrorHeadersResponse> getErrorHeaders(GetErrorHeadersRequest request) {

        return searchEngine.getErrorHeaders(request).stream()
                .map(GetErrorHeadersResponse::fromModel)
                .collect(Collectors.toList());

    }

}
