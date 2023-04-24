package com.dmakarevich.yellow_collector.general_requester.db.model.statistic;

import lombok.Getter;

@Getter
public class GetSummaryDBStatisticsReportResponseElement {

    private final String baseName;
    private final String appName;
    private final String contextErrorName;
    private final long errorCount;

    private GetSummaryDBStatisticsReportResponseElement(String baseName, String appName, String contextErrorName, long errorCount) {
        this.baseName = baseName;
        this.appName = appName;
        this.contextErrorName = contextErrorName;
        this.errorCount = errorCount;
    }

    public static GetSummaryDBStatisticsReportResponseElement of(String baseName,
                                                                 String appName,
                                                                 String contextErrorName,
                                                                 long errorCount ){
        return new GetSummaryDBStatisticsReportResponseElement(baseName, appName, contextErrorName, errorCount);
    }

}
