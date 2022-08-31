package com.dmakarevich.yellow_collector.general_requester.view.responses.base;

import com.dmakarevich.yellow_collector.general_requester.db.model.header.ReportHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorReportHeader {

    private String id;
    private Date time;
    private String clientInfoPlatformType;
    private String clientInfoAppVersion;
    private String sessionInfoUserName;
    private String configInfoName;
    private String clientInfoAppName;
    private String configInfoDescription;
    private String lastLineOfTheErrorContext;
    private String userDescription;
    private String baseName;
    private String group;
    private String serverStackHash;
    private String clientStackHash;

    public static ErrorReportHeader fromModel(ReportHeader model) {

        if (model == null){
            return null;
        }
        return  ErrorReportHeader.builder()
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
                .baseName(model.getBaseName())
                .group(model.getGroup())
                .serverStackHash(model.getServerStackHash())
                .clientStackHash(model.getClientStackHash())
                .build();

    }

}