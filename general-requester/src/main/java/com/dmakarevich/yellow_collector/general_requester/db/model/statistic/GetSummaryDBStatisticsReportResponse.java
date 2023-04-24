package com.dmakarevich.yellow_collector.general_requester.db.model.statistic;

import lombok.Getter;
import java.util.List;

@Getter
public class GetSummaryDBStatisticsReportResponse {

    private final List<GetSummaryDBStatisticsReportResponseElement> result;

    private GetSummaryDBStatisticsReportResponse(List<GetSummaryDBStatisticsReportResponseElement> elements) {
        this.result = elements;
    }

    public static GetSummaryDBStatisticsReportResponse of(List<GetSummaryDBStatisticsReportResponseElement> elements){
        return new GetSummaryDBStatisticsReportResponse(elements);
    }

}
