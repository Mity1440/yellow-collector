
package com.dmakarevich.yellow_collector.db.model.main;

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
@Document(collection = "base-error-info")
public class BasicErrorReportInfo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("clientInfo")
    @Expose
    private ClientInfo clientInfo;
    @SerializedName("sessionInfo")
    @Expose
    private SessionInfo sessionInfo;
    @SerializedName("infoBaseInfo")
    @Expose
    private InfoBaseInfo infoBaseInfo;
    @SerializedName("serverInfo")
    @Expose
    private ServerInfo serverInfo;
    @SerializedName("configInfo")
    @Expose
    private ConfigInfo configInfo;

}
