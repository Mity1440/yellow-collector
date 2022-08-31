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
@Document(indexName = "error-info-details-#{@indexNameProvider.timeSuffix()}",  createIndex = true)
public class ErrorInfoDetail {

    @Id
    private String id;
    private String errorInfoId;
    private String reportId;
    private String messageText;
    private List<String> categories;

}
