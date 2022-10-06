package com.dmakarevich.yellow_collector.sr_receiver.view;

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

    public static GetInfoResponse defaultInfo() {
        return new GetInfoResponse(true,"Ошибка будет обработана",3);
    }
}
