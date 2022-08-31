package com.dmakarevich.yellow_collector.general_requester.view.responses.base;

import com.dmakarevich.yellow_collector.general_requester.db.model.details.ErrorInfoDetail;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ErrorReportInfoDetail {

    private String messageText;
    private List<String> categories;

    public static ErrorReportInfoDetail fromModel(ErrorInfoDetail model){
        if (model == null){
            return null;
        }

        return ErrorReportInfoDetail.builder()
                .messageText(model.getMessageText())
                .categories(model.getCategories().stream().collect(Collectors.toList()))
                .build();

    }

}
