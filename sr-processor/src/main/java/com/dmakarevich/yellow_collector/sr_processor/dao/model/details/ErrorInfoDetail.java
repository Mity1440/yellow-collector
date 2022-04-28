package com.dmakarevich.yellow_collector.sr_processor.dao.model.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "error-info-details-#{@indexNameProvider.timeSuffix()}",  createIndex = true)
public class ErrorInfoDetail {

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
    @SerializedName("messageText")
    @Expose
    public String messageText;
    @SerializedName("categories")
    @Expose
    public List<String> categories = null;

}
