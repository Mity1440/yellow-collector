package com.dmakarevich.yellow_collector.general_requester.controllers;

import com.dmakarevich.yellow_collector.general_requester.services.ErrorSearchService;
import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorReportHeadersRequest;
import com.dmakarevich.yellow_collector.general_requester.view.responses.GetErrorReportInfoDetailsResponse;
import com.dmakarevich.yellow_collector.general_requester.view.responses.GetErrorReportHeadersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ErrorSearchController {

    private final ErrorSearchService service;

    public ErrorSearchController(ErrorSearchService searchEngine) {
        this.service = searchEngine;
    }

    @PostMapping(value = "/report-errors/list")
    public GetErrorReportHeadersResponse GetErrorHeaders(@RequestBody GetErrorReportHeadersRequest request){
        return service.getErrorHeaders(request);
    }

    @PostMapping(value = "/report-errors/details/{id}")
    public GetErrorReportInfoDetailsResponse GetErrorReportDetails(@PathVariable String id){
        return service.getErrorDetails(id);
    }

}
