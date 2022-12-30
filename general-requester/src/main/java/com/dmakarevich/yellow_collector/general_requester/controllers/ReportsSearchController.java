package com.dmakarevich.yellow_collector.general_requester.controllers;

import com.dmakarevich.yellow_collector.general_requester.services.error_report.ReportSearchService;
import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorReportHeadersRequest;
import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.GetErrorReportInfoDetailsResponse;
import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.GetErrorReportHeadersResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value ="/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReportsSearchController {

    private final ReportSearchService service;

    @PostMapping(value = "/report-errors/list")
    public GetErrorReportHeadersResponse getErrorHeaders(@Valid @RequestBody GetErrorReportHeadersRequest request){
        return service.getErrorHeaders(request);
    }

    @PostMapping(value = "/report-errors/details/{id}")
    public GetErrorReportInfoDetailsResponse getErrorReportDetails(@PathVariable String id){
        return service.getErrorDetails(id);
    }

}
