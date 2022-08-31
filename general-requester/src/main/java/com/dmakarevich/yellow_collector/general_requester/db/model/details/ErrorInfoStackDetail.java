package com.dmakarevich.yellow_collector.general_requester.db.model.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "error-info-stack-details-#{@indexNameProvider.timeSuffix()}",  createIndex = true)
public class ErrorInfoStackDetail {

    @Id
    private String id;
    private String errorInfoId;
    private String reportId;
    private String moduleName;
    private Integer lineNumber;
    private String sourceModuleText;

}
