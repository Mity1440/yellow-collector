package com.dmakarevich.yellow_collector.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInfoResponse {

    private boolean needSendReport;
    private String userMessage ;
    private int dumpType;

}
