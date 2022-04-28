
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
public class SystemInfo {

    @SerializedName("osVersion")
    @Expose
    @Field(type = FieldType.Text, name = "osVersion")
    private String osVersion;

    @SerializedName("fullRAM")
    @Expose
    @Field(type = FieldType.Long, name = "fullRAM")
    private Long fullRAM;

    @SerializedName("freeRAM")
    @Expose
    @Field(type = FieldType.Long, name = "freeRAM")
    private Long freeRAM;

    @SerializedName("processor")
    @Expose
    @Field(type = FieldType.Text, name = "processor")
    private String processor;

    @SerializedName("clientID")
    @Expose
    @Field(type = FieldType.Text, name = "clientID")
    private String clientID;

}
