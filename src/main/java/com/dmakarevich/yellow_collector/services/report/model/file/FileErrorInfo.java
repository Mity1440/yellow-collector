
package com.dmakarevich.yellow_collector.services.report.model.file;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileErrorInfo {

    @SerializedName("systemErrorInfo")
    @Expose
    private FileSystemErrorInfo systemErrorInfo;

    @SerializedName("applicationErrorInfo")
    @Expose
    private FileApplicationErrorInfo applicationErrorInfo;

}
