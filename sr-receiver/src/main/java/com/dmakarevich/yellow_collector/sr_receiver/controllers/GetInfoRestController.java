package com.dmakarevich.yellow_collector.sr_receiver.controllers;

import com.dmakarevich.yellow_collector.sr_receiver.services.processors.GetInfoProcessor;
import com.dmakarevich.yellow_collector.sr_receiver.views.GetInfoRequest;
import com.dmakarevich.yellow_collector.sr_receiver.views.GetInfoResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetInfoRestController {

    private final GetInfoProcessor processor;

    public GetInfoRestController(GetInfoProcessor processor) {
        this.processor = processor;
    }

    @PostMapping(value = "/getInfo")
    public GetInfoResponse getInfo(@RequestBody GetInfoRequest request){
        return processor.getInfoAboutError(request);
    }

}
