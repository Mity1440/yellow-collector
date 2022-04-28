package com.dmakarevich.yellow_collector.general_requester.view.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetErrorHeadersRequest {

    private Date from;
    private Date to;

}
