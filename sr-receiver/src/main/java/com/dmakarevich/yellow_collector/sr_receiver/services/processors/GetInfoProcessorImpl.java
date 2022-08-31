package com.dmakarevich.yellow_collector.sr_receiver.services.processors;

import com.dmakarevich.yellow_collector.sr_receiver.views.GetInfoRequest;
import com.dmakarevich.yellow_collector.sr_receiver.views.GetInfoResponse;
import org.springframework.stereotype.Service;

@Service
public class GetInfoProcessorImpl implements GetInfoProcessor{

    @Override
    public GetInfoResponse getInfoAboutError(GetInfoRequest request){
        return GetInfoResponse.defaultInfo(); // Пока используем заглушку
    }

}
