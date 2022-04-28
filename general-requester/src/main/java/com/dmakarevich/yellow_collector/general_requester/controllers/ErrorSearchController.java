package com.dmakarevich.yellow_collector.general_requester.controllers;

import com.dmakarevich.yellow_collector.general_requester.services.ErrorSearchService;
import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorHeadersRequest;
import com.dmakarevich.yellow_collector.general_requester.view.responses.GetErrorHeadersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ErrorSearchController {

    private final ErrorSearchService searchEngine;

    public ErrorSearchController(ErrorSearchService searchEngine) {
        this.searchEngine = searchEngine;
    }

    @PostMapping(value = "/report-errors/list")
    public List<GetErrorHeadersResponse> GetErrorHeaders(@RequestBody GetErrorHeadersRequest request){
        return searchEngine.getErrorHeaders(request);
    }

}
