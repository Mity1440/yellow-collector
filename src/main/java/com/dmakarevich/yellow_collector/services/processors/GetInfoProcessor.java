package com.dmakarevich.yellow_collector.services.processors;

import com.dmakarevich.yellow_collector.views.GetInfoRequest;
import com.dmakarevich.yellow_collector.views.GetInfoResponse;

public interface GetInfoProcessor {

    GetInfoResponse getInfoAboutError(GetInfoRequest request);

}
