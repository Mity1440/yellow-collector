
package com.dmakarevich.yellow_collector.sr_processor.db.model.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
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
@Document(indexName = "error-info-#{@indexNameProvider.timeSuffix()}",  createIndex = true)
public class ErrorInfo {

    @SerializedName("id")
    @Expose
    @Id
    private String id;
    @SerializedName("reportId")
    @Expose
    private String reportId;
    @SerializedName("systemErrorInfo")
    @Expose
    private SystemErrorInfo systemErrorInfo;
    @SerializedName("applicationErrorInfo")
    @Expose
    private ApplicationErrorInfo applicationErrorInfo;

}
