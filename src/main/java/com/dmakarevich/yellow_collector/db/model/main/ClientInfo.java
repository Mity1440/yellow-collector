
package com.dmakarevich.yellow_collector.db.model.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientInfo {

    @SerializedName("platformType")
    @Expose
    private String platformType;
    @SerializedName("appVersion")
    @Expose
    private String appVersion;
    @SerializedName("appName")
    @Expose
    private String appName;
    @SerializedName("systemInfo")
    @Expose
    private SystemInfo systemInfo;

}
