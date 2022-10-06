package com.dmakarevich.yellow_collector.sr_processor.config;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class IndexNameProvider {
    public String timeSuffix(){
        return DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now(ZoneOffset.UTC));
    }
}
