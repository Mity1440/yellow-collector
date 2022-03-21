package com.dmakarevich.yellow_collector.db.model.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "error-info-stack-detail")
public class ErrorInfoStackDetail {

    @SerializedName("id")
    @Expose
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
