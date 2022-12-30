package com.dmakarevich.yellow_collector.sr_receiver.controller;

import com.dmakarevich.yellow_collector.sr_receiver.services.processor.GetInfoProcessor;
import com.dmakarevich.yellow_collector.sr_receiver.view.GetInfoRequest;
import com.dmakarevich.yellow_collector.sr_receiver.view.GetInfoResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
