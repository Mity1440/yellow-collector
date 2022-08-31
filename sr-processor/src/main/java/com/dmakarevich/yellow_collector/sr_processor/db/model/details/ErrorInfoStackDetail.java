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
@Document(indexName = "error-stack-#{@indexNameProvider.timeSuffix()}",  createIndex = true)
public class ErrorInfoStackDetail {

    @SerializedName("id")
    @Expose
    @Id
    public String id;

    @SerializedName("errorInfoId")
    @Expose
    public String errorInfoId;
    @SerializedName("reportId")
    @Expose
    public String reportId;
    @SerializedName("moduleName")
    @Expose
    public String moduleName;
    @SerializedName("lineNumber")
    @Expose
    public Integer lineNumber;
    @SerializedName("sourceModuleText")
    @Expose
    public String sourceModuleText;

}
