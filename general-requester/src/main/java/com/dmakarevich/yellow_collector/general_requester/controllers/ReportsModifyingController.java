package com.dmakarevich.yellow_collector.general_requester.controllers;

import com.dmakarevich.yellow_collector.general_requester.services.error_report.ReportModifyingService;
import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.MarkForDeletionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReportsModifyingController {

    private final ReportModifyingService service;

    @PostMapping(value = "/report-errors/marked-for-deletion/{id}")
    public MarkForDeletionResponse markForDeletionByID(@PathVariable String id){

        String result = "ok";
        try {
            service.markForDeletion(id);
        } catch (Exception e){
            result = e.getMessage();
        }

        return MarkForDeletionResponse.of(result, id);

    }

}
