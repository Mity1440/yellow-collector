
package com.dmakarevich.yellow_collector.general_requester.dao.model.header;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientInfo {

    @SerializedName("platformType")
    @Expose
    @Field(type = FieldType.Text, name = "platformType")
    private String platformType;

    @SerializedName("appVersion")
    @Expose
    @Field(type = FieldType.Text, name = "appVersion")
    private String appVersion;

    @SerializedName("appName")
    @Expose
    @Field(type = FieldType.Text, name = "appName")
    private String appName;

    @SerializedName("systemInfo")
    @Expose
    private SystemInfo systemInfo;

}
