package com.dmakarevich.yellow_collector.controllers;

import com.dmakarevich.yellow_collector.services.processors.GetInfoProcessor;
import com.dmakarevich.yellow_collector.views.GetInfoRequest;
import com.dmakarevich.yellow_collector.views.GetInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetInfoRestController {

    private final GetInfoProcessor processor;

    @Autowired
    public GetInfoRestController(GetInfoProcessor processor) {
        this.processor = processor;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getInfo")
    public GetInfoResponse getInfo(@RequestBody GetInfoRequest request){
        return processor.getInfoAboutError(request);
    }

}
