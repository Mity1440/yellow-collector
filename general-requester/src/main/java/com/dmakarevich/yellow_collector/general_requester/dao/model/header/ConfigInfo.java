
package com.dmakarevich.yellow_collector.general_requester.dao.model.header;

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
public class ConfigInfo {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("compatibilityMode")
    @Expose
    private String compatibilityMode;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("changeEnabled")
    @Expose
    private Boolean changeEnabled;

}
