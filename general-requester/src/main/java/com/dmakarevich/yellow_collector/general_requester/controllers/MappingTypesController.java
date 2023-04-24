package com.dmakarevich.yellow_collector.general_requester.controllers;

import com.dmakarevich.yellow_collector.general_requester.services.mapping.MappingTypesService;
import com.dmakarevich.yellow_collector.general_requester.view.responses.mapping.MappingTypeDeclaration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class MappingTypesController {

    private final MappingTypesService service;

    @PostMapping(value = "/report-errors/list/_mapping")
    public MappingTypeDeclaration getErrorHeadersMapping(){
        return service.getErrorHeadersMapping();
    }

    @PostMapping(value = "/report-errors/statistics/summary/_mapping")
    public MappingTypeDeclaration getSummaryStatisticsReportMapping(){
        return service.getSummaryStatisticsReportMapping();
    }


}
