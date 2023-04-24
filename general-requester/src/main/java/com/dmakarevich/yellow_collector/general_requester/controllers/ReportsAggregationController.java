package com.dmakarevich.yellow_collector.general_requester.controllers;

import com.dmakarevich.yellow_collector.general_requester.services.statistics.ReportsStatisticsService;

import com.dmakarevich.yellow_collector.general_requester.view.requests.GetBaseErrorReportAggregation;

import com.dmakarevich.yellow_collector.general_requester.view.responses.statistics.GetSummaryStatisticsReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReportsAggregationController {

    private final ReportsStatisticsService service;

    @PostMapping(value = "/report-errors/statistics/summary")
    public GetSummaryStatisticsReportResponse getSummaryStatisticsReport(GetBaseErrorReportAggregation request){
        return service.getSummaryStatistics(request);
    }

}
