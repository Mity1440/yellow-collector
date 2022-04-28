package com.dmakarevich.yellow_collector.general_requester.view.responses;

import com.dmakarevich.yellow_collector.general_requester.dao.model.header.ReportHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetErrorHeadersResponse {

    private String id;
    private Date time;
    private String clientInfoPlatformType;
    private String clientInfoAppVersion;
    private String clientInfoAppName;
    private String sessionInfoUserName;
    private String configInfoName;
    private String configInfoDescription;
    private String lastLineOfTheErrorContext;
    private String userDescription;

    public static GetErrorHeadersResponse fromModel(ReportHeader model) {

        var builder = new GetErrorHeadersResponseBuilder();

        return builder
                .id(model.getId())
                .time(model.getTime())
                .clientInfoAppName(model.getClientInfoAppName())
                .clientInfoAppVersion(model.getClientInfoAppVersion())
                .clientInfoPlatformType(model.getClientInfoPlatformType())
                .sessionInfoUserName(model.getSessionInfoUserName())
                .configInfoName(model.getConfigInfoName())
                .configInfoDescription(model.getConfigInfoDescription())
                .lastLineOfTheErrorContext(model.getLastLineOfTheErrorContext())
                .userDescription(model.getUserDescription())
                .build();

    }


}
