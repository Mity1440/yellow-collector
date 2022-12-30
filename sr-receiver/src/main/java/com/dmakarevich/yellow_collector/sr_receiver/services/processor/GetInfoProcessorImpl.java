package com.dmakarevich.yellow_collector.sr_receiver.services.processor;

import com.dmakarevich.yellow_collector.sr_receiver.view.GetInfoRequest;
import com.dmakarevich.yellow_collector.sr_receiver.view.GetInfoResponse;
import org.springframework.stereotype.Service;

@Service
public class GetInfoProcessorImpl implements GetInfoProcessor{

    @Override
    public GetInfoResponse getInfoAboutError(GetInfoRequest request){
        return GetInfoResponse.defaultInfo();
    }

}
