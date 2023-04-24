package com.dmakarevich.yellow_collector.general_requester.view.responses.statistics;

import com.dmakarevich.yellow_collector.general_requester.db.model.statistic.GetSummaryDBStatisticsReportResponseElement;
import lombok.Data;

@Data
public class GetSummaryStatisticsReportResponseElement {

    private final String baseName;
    private final String appName;
    private final String contextErrorName;
    private final long errorCount;

    private GetSummaryStatisticsReportResponseElement(String baseName,
                                                      String appName,
                                                      String contextErrorName,
                                                      long errorCount) {
        this.baseName = baseName;
        this.appName = appName;
        this.contextErrorName = contextErrorName;
        this.errorCount = errorCount;

    }

    public static GetSummaryStatisticsReportResponseElement from(GetSummaryDBStatisticsReportResponseElement model){
        return new GetSummaryStatisticsReportResponseElement(model.getBaseName(),
                                                             model.getAppName(),
                                                             model.getContextErrorName(),
                                                             model.getErrorCount());
    }

}
