
package com.dmakarevich.yellow_collector.sr_processor.db.model.details;

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
public class SystemErrorInfo {

    @SerializedName("clientStack")
    @Expose
    private String clientStack;
    @SerializedName("clientStackHash")
    @Expose
    private String clientStackHash;
    @SerializedName("serverStack")
    @Expose
    private String serverStack;
    @SerializedName("serverStackHash")
    @Expose
    private String serverStackHash;
    @SerializedName("systemCrash")
    @Expose
    private boolean systemCrash;

}
