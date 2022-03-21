
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
@Document(collection = "error-info")
public class ErrorInfo {

    @SerializedName("id")
    @Expose
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
