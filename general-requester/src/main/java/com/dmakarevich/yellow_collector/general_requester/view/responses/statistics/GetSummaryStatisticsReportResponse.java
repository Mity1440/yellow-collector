package com.dmakarevich.yellow_collector.general_requester.view.responses.statistics;

import com.dmakarevich.yellow_collector.general_requester.db.model.statistic.GetSummaryDBStatisticsReportResponse;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GetSummaryStatisticsReportResponse {

    private final List<GetSummaryStatisticsReportResponseElement> elements;

    private GetSummaryStatisticsReportResponse(List<GetSummaryStatisticsReportResponseElement> elements) {
        this.elements = elements;
    }

    public static GetSummaryStatisticsReportResponse from(GetSummaryDBStatisticsReportResponse model){
        return new GetSummaryStatisticsReportResponse(model.getResult().stream()
                .map(GetSummaryStatisticsReportResponseElement::from)
                .collect(Collectors.toList()));
    }
}
