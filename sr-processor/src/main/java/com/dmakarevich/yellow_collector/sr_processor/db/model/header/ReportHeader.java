
package com.dmakarevich.yellow_collector.sr_processor.db.model.header;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "report-headers-#{@indexNameProvider.timeSuffix()}",  createIndex = true)
public class ReportHeader {

    @SerializedName("id")
    @Expose
    @Id
    private String id;

    @SerializedName("time")
    @Expose
    @Field(type = FieldType.Date, name = "time")
    private Date time;

    @SerializedName("clientInfo")
    @Expose
    @Field(type = FieldType.Nested, name = "clientInfo")
    private ClientInfo clientInfo;

    @SerializedName("sessionInfo")
    @Expose
    private SessionInfo sessionInfo;

    @SerializedName("infoBaseInfo")
    @Expose
    private InfoBaseInfo infoBaseInfo;

    @SerializedName("serverInfo")
    @Expose
    private ServerInfo serverInfo;

    @SerializedName("configInfo")
    @Expose
    private ConfigInfo configInfo;

}
