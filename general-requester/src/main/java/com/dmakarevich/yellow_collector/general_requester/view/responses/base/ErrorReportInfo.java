package com.dmakarevich.yellow_collector.general_requester.view.responses.base;

import com.dmakarevich.yellow_collector.general_requester.db.model.details.ErrorInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorReportInfo {

    private String userDescription;
    private String clientStack;
    private String clientStackHash;
    private String serverStack;
    private String serverStackHash;
    private boolean systemCrash;
    private String stackHash;

    public static ErrorReportInfo fromModel(ErrorInfo model){
        if (model == null){
            return null;
        }
        return ErrorReportInfo.builder()
                .userDescription(model.getUserDescription())
                .clientStack(model.getClientStack())
                .clientStackHash(model.getClientStackHash())
                .serverStack(model.getServerStack())
                .serverStackHash(model.getClientStackHash())
                .systemCrash(model.isSystemCrash())
                .stackHash(model.getStackHash())
                .build();
    }

}
