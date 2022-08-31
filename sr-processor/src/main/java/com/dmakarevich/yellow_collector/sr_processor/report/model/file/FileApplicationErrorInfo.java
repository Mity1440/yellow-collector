
package com.dmakarevich.yellow_collector.sr_processor.report.model.file;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileApplicationErrorInfo {

    @SerializedName("errors")
    @Expose
    private FileApplicationErrorInfoErrors errors;

    @SerializedName("stack")
    @Expose
    private List<List<String>> stack = null;

    @SerializedName("stackHash")
    @Expose
    private String stackHash;

}
