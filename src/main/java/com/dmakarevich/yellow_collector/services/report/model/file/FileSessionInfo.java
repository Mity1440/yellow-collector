
package com.dmakarevich.yellow_collector.services.report.model.file;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileSessionInfo {

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
