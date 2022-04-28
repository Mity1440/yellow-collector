
package com.dmakarevich.yellow_collector.general_requester.dao.model.header;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "report-headers", createIndex = true)
public class ReportHeader {

    @Id
    private String id;

    @Field(type = FieldType.Date, name = "time")
    private Date time;

    @Field(type = FieldType.Nested, name = "clientInfo")
    private ClientInfo clientInfo;

    private SessionInfo sessionInfo;

    private InfoBaseInfo infoBaseInfo;

    private ServerInfo serverInfo;

    private ConfigInfo configInfo;

    private String userDescription;

    private String lastLineOfTheErrorContext;

    public String getClientInfoAppVersion(){
        return clientInfo == null? "" : clientInfo.getAppVersion();
    }

    public String getClientInfoAppName(){
        return clientInfo == null? "" : clientInfo.getAppName();
    }

    public String getClientInfoPlatformType(){
        return clientInfo == null? "" : clientInfo.getPlatformType();
    }

    public String getSessionInfoUserName(){
        return sessionInfo == null? "" : sessionInfo.getUserName();
    }

    public String getConfigInfoName(){
        return configInfo == null? "" : configInfo.getName();
    }

    public String getConfigInfoDescription(){
        return configInfo == null? "" : configInfo.getDescription();
    }

}
