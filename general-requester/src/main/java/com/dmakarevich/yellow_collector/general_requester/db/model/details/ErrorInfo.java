
package com.dmakarevich.yellow_collector.general_requester.db.model.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "error-info-#{@indexNameProvider.timeSuffix()}",  createIndex = true)
public class ErrorInfo {

    @Id
    private String id;
    private String reportId;
    private String userDescription;
    private SystemErrorInfo systemErrorInfo;
    private ApplicationErrorInfo applicationErrorInfo;

    public String getClientStack() {
        return systemErrorInfo == null? "" : systemErrorInfo.getClientStack();
    }

    public String getClientStackHash() {
        return systemErrorInfo == null? "" : systemErrorInfo.getClientStackHash();
    }

    public String getServerStack() {
        return systemErrorInfo == null? "" : systemErrorInfo.getServerStack();
    }

    public String getServerStackHash() {
        return systemErrorInfo == null? "" : systemErrorInfo.getServerStackHash();
    }

    public boolean isSystemCrash() {
        return systemErrorInfo == null? true : systemErrorInfo.isSystemCrash();
    }

    public String getStackHash() {
        return applicationErrorInfo == null? "" : applicationErrorInfo.getStackHash();
    }

}
