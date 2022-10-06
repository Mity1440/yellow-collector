package com.dmakarevich.yellow_collector.sr_receiver.controller;

import com.dmakarevich.yellow_collector.sr_receiver.services.processor.PushReportProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PushReportRestController {

    private final PushReportProcessor processor;

    @Autowired
    public PushReportRestController(PushReportProcessor processor) {this.processor = processor;}

    @PostMapping(value = "/pushReport", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void pushReport(HttpServletRequest request){
        processor.processReportFile(getMultipartFileFromRequest(request));
    }

    private MultipartFile getMultipartFileFromRequest(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multipartRequest.getFileNames();
        return multipartRequest.getFile(it.next());
    }


}
