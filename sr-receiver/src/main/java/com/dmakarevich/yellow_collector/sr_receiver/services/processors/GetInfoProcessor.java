package com.dmakarevich.yellow_collector.sr_receiver.services.processors;

import com.dmakarevich.yellow_collector.sr_receiver.views.GetInfoRequest;
import com.dmakarevich.yellow_collector.sr_receiver.views.GetInfoResponse;

public interface GetInfoProcessor {
    GetInfoResponse getInfoAboutError(GetInfoRequest request);
}
