package com.dmakarevich.yellow_collector.general_requester.view.responses.errors.base;

import com.dmakarevich.yellow_collector.general_requester.db.model.details.ErrorInfoStackDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorReportInfoStackDetail {

    private String moduleName;
    private Integer lineNumber;
    private String sourceModuleText;

    public static ErrorReportInfoStackDetail fromModel(ErrorInfoStackDetail model) {
        if (model == null){
            return null;
        }

        return ErrorReportInfoStackDetail.builder()
                .lineNumber(model.getLineNumber())
                .moduleName(model.getModuleName())
                .sourceModuleText(model.getSourceModuleText())
                .build();

    }
}
