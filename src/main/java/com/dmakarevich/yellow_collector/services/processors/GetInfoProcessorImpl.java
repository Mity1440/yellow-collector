package com.dmakarevich.yellow_collector.services.processors;

import com.dmakarevich.yellow_collector.db.services.GetInfoDBService;
import com.dmakarevich.yellow_collector.db.services.model.ErrorInfoRequest;
import com.dmakarevich.yellow_collector.db.services.model.ErrorInfoSettings;
import com.dmakarevich.yellow_collector.views.GetInfoRequest;
import com.dmakarevich.yellow_collector.views.GetInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetInfoProcessorImpl implements GetInfoProcessor{

    private final GetInfoDBService dbService;

    @Autowired
    public GetInfoProcessorImpl(GetInfoDBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public GetInfoResponse getInfoAboutError(GetInfoRequest request){

        var errorInfo = dbService.get(convertInfoRequestToErrorInfoRequest(request));
        return convertErrorInfoSettingsToGetInfoResponse(errorInfo);

    }

    private GetInfoResponse convertErrorInfoSettingsToGetInfoResponse(ErrorInfoSettings errorInfo) {

        var getInfoResponse = new GetInfoResponse();
        getInfoResponse.setNeedSendReport(errorInfo.isNeedSendReport());
        getInfoResponse.setUserMessage(errorInfo.getUserMessage());

        return getInfoResponse;

    }

    private ErrorInfoRequest convertInfoRequestToErrorInfoRequest(GetInfoRequest request){

        var errorInfoRequest = new ErrorInfoRequest();
        errorInfoRequest.setConfigName(request.getConfigName());
        errorInfoRequest.setServerStackHash(request.getServerStackHash());

        return errorInfoRequest;

    }

}
