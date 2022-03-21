
package com.dmakarevich.yellow_collector.services.report.model.file;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileSystemInfo {

    @SerializedName("osVersion")
    @Expose
    private String osVersion;

    @SerializedName("fullRAM")
    @Expose
    private Long fullRAM;

    @SerializedName("freeRAM")
    @Expose
    private Long freeRAM;

    @SerializedName("processor")
    @Expose
    private String processor;

    @SerializedName("clientID")
    @Expose
    private String clientID;

}
