
package com.dmakarevich.yellow_collector.sr_processor.report.model.file;

import com.dmakarevich.yellow_collector.sr_processor.dao.model.ReportErrorInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileReportErrorInfo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("time")
    @Expose
    private java.util.Date time;

    @SerializedName("clientInfo")
    @Expose
    private FileClientInfo clientInfo;

    @SerializedName("sessionInfo")
    @Expose
    private FileSessionInfo sessionInfo;

    @SerializedName("infoBaseInfo")
    @Expose
    private FileInfoBaseInfo infoBaseInfo;

    @SerializedName("serverInfo")
    @Expose
    private FileServerInfo serverInfo;

    @SerializedName("configInfo")
    @Expose
    private FileConfigInfo configInfo;

    @SerializedName("errorInfo")
    @Expose
    private FileErrorInfo errorInfo;

    @SerializedName("screenshot")
    @Expose
    private FileScreenshot screenshot;

    public ReportErrorInfo toModel() {
        return ReportErrorInfo.fromFileErrorInfo(this);
    }
}
