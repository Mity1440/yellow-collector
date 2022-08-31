
package com.dmakarevich.yellow_collector.sr_processor.db.model.header;

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

    @Id
    private String id;

    // Поля явно получаемые из отчета об ошибке. Служат для характеристики ошибки в целом
    @Field(type = FieldType.Date, name = "time")
    private Date time;
    @Field(type = FieldType.Nested, name = "clientInfo")
    private ClientInfo clientInfo;
    @Field(type = FieldType.Nested, name = "sessionInfo")
    private SessionInfo sessionInfo;
    @Field(type = FieldType.Nested, name = "infoBaseInfo")
    private InfoBaseInfo infoBaseInfo;
    @Field(type = FieldType.Nested, name = "serverInfo")
    private ServerInfo serverInfo;
    @Field(type = FieldType.Nested, name = "configInfo")
    private ConfigInfo configInfo;

    // Поля, которые будут использоваться для денормализации, чтобы упростить запросы для клиентов
    // без необходимости каскадных запросов
    @Field(type = FieldType.Text, name = "userDescription")
    private String userDescription;
    @Field(type = FieldType.Text, name = "lastLineOfTheErrorContext")
    private String lastLineOfTheErrorContext;
    // Группировочные поля
    @Field(type = FieldType.Text, name = "baseName")
    private String baseName;
    @Field(type = FieldType.Text, name = "group")
    private String group;
    @Field(type = FieldType.Text, name = "serverStackHash")
    private String serverStackHash;
    @Field(type = FieldType.Text, name = "clientStackHash")
    private String clientStackHash;
}
