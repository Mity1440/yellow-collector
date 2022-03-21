
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
public class ServerInfo {

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
