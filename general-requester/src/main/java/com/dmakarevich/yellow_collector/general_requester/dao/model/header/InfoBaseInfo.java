
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
public class InfoBaseInfo {

    @SerializedName("localeCode")
    @Expose
    private String localeCode;

}
