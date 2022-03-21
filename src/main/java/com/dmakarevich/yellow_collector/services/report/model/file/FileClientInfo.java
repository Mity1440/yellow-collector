
package com.dmakarevich.yellow_collector.services.report.model.file;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileClientInfo {

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
    private FileSystemInfo systemInfo;

}
