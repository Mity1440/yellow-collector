
package com.dmakarevich.yellow_collector.sr_processor.dao.model.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationErrorInfo {

    @SerializedName("errors")
    @Expose
    private List<String> errors = null;
    @SerializedName("stack")
    @Expose
    private List<String> stack = null;
    @SerializedName("stackHash")
    @Expose
    private String stackHash;

}
