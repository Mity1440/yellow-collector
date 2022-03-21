
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
public class SystemInfo {

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
