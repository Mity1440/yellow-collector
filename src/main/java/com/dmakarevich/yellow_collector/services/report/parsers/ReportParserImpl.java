package com.dmakarevich.yellow_collector.services.report.parsers;

import com.dmakarevich.yellow_collector.services.report.model.file.FileApplicationErrorInfoErrorsElement;
import com.dmakarevich.yellow_collector.services.report.model.file.FileReportErrorInfo;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReportParserImpl implements ReportParser{

    private final Gson gson;

    @Autowired
    public ReportParserImpl() {
        this.gson = new GsonBuilder()
                         .registerTypeAdapter(FileApplicationErrorInfoErrorsElement.class,
                        new ApplicationErrorInfoErrorsElementDeserializer()).create();
    }

    private final class ApplicationErrorInfoErrorsElementDeserializer implements JsonDeserializer<FileApplicationErrorInfoErrorsElement> {

        @Override
        public FileApplicationErrorInfoErrorsElement deserialize(JsonElement json,
                                                                 Type typeOfT,
                                                                 JsonDeserializationContext context) throws JsonParseException {

            var jsonArray = json.getAsJsonArray();

            var errorDescription = jsonArray.get(0).getAsString();

            List<String> errorCategories = new ArrayList<>();
            var errorDescriptionInJsonArray = jsonArray.get(1).getAsJsonArray();
            for (var errorDescriptionInJsonArrayElement: errorDescriptionInJsonArray){
                errorCategories.add(errorDescriptionInJsonArrayElement.getAsString());
            }

            return new FileApplicationErrorInfoErrorsElement(errorDescription, errorCategories);

        }
    }

    @Override
    public FileReportErrorInfo parse(String reportInfoInJson){
        return gson.fromJson(reportInfoInJson, FileReportErrorInfo.class);
    }

}
