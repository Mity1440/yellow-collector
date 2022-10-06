package com.dmakarevich.yellow_collector.sr_receiver.services.processor;

import com.dmakarevich.yellow_collector.sr_receiver.view.GetInfoRequest;
import com.dmakarevich.yellow_collector.sr_receiver.view.GetInfoResponse;

public interface GetInfoProcessor {
    GetInfoResponse getInfoAboutError(GetInfoRequest request);
}
