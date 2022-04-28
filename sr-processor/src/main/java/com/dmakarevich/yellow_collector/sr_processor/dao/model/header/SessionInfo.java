
package com.dmakarevich.yellow_collector.sr_processor.dao.model.header;

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
public class SessionInfo {

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("dataSeparation")
    @Expose
    private String dataSeparation;
    @SerializedName("platformInterfaceLanguageCode")
    @Expose
    private String platformInterfaceLanguageCode;
    @SerializedName("configurationInterfaceLanguageCode")
    @Expose
    private String configurationInterfaceLanguageCode;
    @SerializedName("localeCode")
    @Expose
    private String localeCode;

}
