
package com.dmakarevich.yellow_collector.sr_processor.report.model.file;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileServerInfo {

    @SerializedName("appVersion")
    @Expose
    private String appVersion;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("dbms")
    @Expose
    private String dbms;

}
