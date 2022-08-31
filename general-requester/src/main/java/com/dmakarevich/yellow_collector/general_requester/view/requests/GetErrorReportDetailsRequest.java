package com.dmakarevich.yellow_collector.general_requester.view.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetErrorReportDetailsRequest {
    private String id;
}
