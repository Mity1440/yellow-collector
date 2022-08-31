package com.dmakarevich.yellow_collector.sr_processor.report;

import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileAdditionalInfo;
import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileApplicationErrorInfoErrors;
import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileApplicationErrorInfoErrorsElement;
import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileReportErrorInfo;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReportParser {

    private final static Gson gson;
    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(FileApplicationErrorInfoErrors.class,
                        new FileApplicationErrorInfoErrorsDeserializer())
                .registerTypeAdapter(FileAdditionalInfo.class,
                        new AdditionalInfoDeserializer())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.X")
                .create();

    }

    private static final class FileApplicationErrorInfoErrorsDeserializer
            implements JsonDeserializer<FileApplicationErrorInfoErrors> {

        @Override
        public FileApplicationErrorInfoErrors deserialize(JsonElement json,
                                                          Type typeOfT,
                                                          JsonDeserializationContext context) throws JsonParseException {

            List<FileApplicationErrorInfoErrorsElement> errors = new ArrayList<>();
            //  Массив описаний всех ошибок. Массив состоит из массивов, каждый из которых состоит из двух элементов:
            // - Строка с текстом ошибки.
            // - Массив строк категорий ошибки. В свою очередь этот массив состоит из строк
            if (json instanceof JsonArray){
                fillErrorsFromJsonArray(errors, json);
            }

            return new FileApplicationErrorInfoErrors(errors);

        }

        private void fillErrorsFromJsonArray(List<FileApplicationErrorInfoErrorsElement> errors, JsonElement json) {

            var jsonArrayExternal = json.getAsJsonArray();
            for (var jsonArrayElement: jsonArrayExternal){
                errors.add(getErrorElementFromJsonElement(jsonArrayElement));
            }

        }

        private FileApplicationErrorInfoErrorsElement getErrorElementFromJsonElement(JsonElement jsonElement){

            List<String> errorCategories = new ArrayList<>();
            String errorDescription = "empty";

            if (jsonElement instanceof JsonArray){
                var jsonArrayInternal = jsonElement.getAsJsonArray();
                for (var jsonArrayInternalElement: jsonArrayInternal){
                    if (jsonArrayInternalElement instanceof JsonArray){
                        for (var errorDescriptionInJsonArrayElement: jsonArrayInternalElement.getAsJsonArray()){
                            errorCategories.add(errorDescriptionInJsonArrayElement.getAsString());
                        }
                    } else{
                        errorDescription = jsonArrayInternalElement.getAsString();
                    }
                }
            }

            return new FileApplicationErrorInfoErrorsElement(errorDescription, errorCategories);

        }

    }

    private static class AdditionalInfoDeserializer
            implements JsonDeserializer<FileAdditionalInfo>{

        private final static Gson gson = new GsonBuilder().create();

        @Override
        public FileAdditionalInfo deserialize(JsonElement json,
                                              Type typeOfT,
                                              JsonDeserializationContext context) throws JsonParseException {
            return gson.fromJson(json.getAsString(), FileAdditionalInfo.class);
        }
    }

    public static FileReportErrorInfo parse(String reportInfoInJson){
        return gson.fromJson(reportInfoInJson, FileReportErrorInfo.class);
    }

}
