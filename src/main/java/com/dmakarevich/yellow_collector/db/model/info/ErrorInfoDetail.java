package com.dmakarevich.yellow_collector.db.model.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "error-info-detail")
public class ErrorInfoDetail {

    @SerializedName("id")
    @Expose
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
