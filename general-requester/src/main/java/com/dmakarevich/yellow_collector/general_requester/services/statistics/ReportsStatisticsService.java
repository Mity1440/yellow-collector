package com.dmakarevich.yellow_collector.general_requester.services.statistics;

import com.dmakarevich.yellow_collector.general_requester.view.requests.GetBaseErrorReportAggregation;
import com.dmakarevich.yellow_collector.general_requester.view.responses.statistics.GetSummaryStatisticsReportResponse;

public interface ReportsStatisticsService {
    GetSummaryStatisticsReportResponse getSummaryStatistics(GetBaseErrorReportAggregation request);
}
