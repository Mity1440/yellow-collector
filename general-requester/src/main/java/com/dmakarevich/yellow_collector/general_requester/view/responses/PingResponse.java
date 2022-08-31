package com.dmakarevich.yellow_collector.general_requester.view.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PingResponse {

    private String version;
    private Long time;

}
