
package com.dmakarevich.yellow_collector.services.report.model.file;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileSystemErrorInfo {

    @SerializedName("clientStack")
    @Expose
    private String clientStack;

    @SerializedName("clientStackHash")
    @Expose
    private String clientStackHash;

}
