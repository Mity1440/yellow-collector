
package com.dmakarevich.yellow_collector.services.report.model.file;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileApplicationErrorInfo {

    @SerializedName("errors")
    @Expose
    private List<FileApplicationErrorInfoErrorsElement> errors = null;

    @SerializedName("stack")
    @Expose
    private List<List<String>> stack = null;

    @SerializedName("stackHash")
    @Expose
    private String stackHash;

}
