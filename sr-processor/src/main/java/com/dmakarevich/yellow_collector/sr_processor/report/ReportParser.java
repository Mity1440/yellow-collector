package com.dmakarevich.yellow_collector.sr_processor.report;

import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileApplicationErrorInfoErrorsElement;
import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileReportErrorInfo;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import java.util.TimeZone;

public class ReportParser {

    private final static Gson gson;
    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(FileApplicationErrorInfoErrorsElement.class,
                        new ApplicationErrorInfoErrorsElementDeserializer())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.X")
                .create();

    }

    private static final class ApplicationErrorInfoErrorsElementDeserializer
            implements JsonDeserializer<FileApplicationErrorInfoErrorsElement> {

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

    public static FileReportErrorInfo parse(String reportInfoInJson){
        return gson.fromJson(reportInfoInJson, FileReportErrorInfo.class);
    }

}
