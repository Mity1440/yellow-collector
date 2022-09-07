package com.dmakarevich.yellow_collector.general_requester.controllers;

import com.dmakarevich.yellow_collector.general_requester.view.responses.support.PingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

@RestController
@RequestMapping(value ="/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class HelpController {

    @RequestMapping(value = "/ping", method = {RequestMethod.GET, RequestMethod.POST})
    public PingResponse GetErrorHeaders(){
        return new PingResponse("", LocalDateTime.now().getLong(ChronoField.NANO_OF_SECOND));
    }

}
