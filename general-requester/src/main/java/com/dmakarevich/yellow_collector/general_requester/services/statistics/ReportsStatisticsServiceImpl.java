package com.dmakarevich.yellow_collector.general_requester.services.statistics;

import com.dmakarevich.yellow_collector.general_requester.db.service.ReportsAggregationDBService;
import com.dmakarevich.yellow_collector.general_requester.view.requests.GetBaseErrorReportAggregation;
import com.dmakarevich.yellow_collector.general_requester.view.responses.statistics.GetSummaryStatisticsReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportsStatisticsServiceImpl implements ReportsStatisticsService{

    private final ReportsAggregationDBService service;

    @Override
    public GetSummaryStatisticsReportResponse getSummaryStatistics(GetBaseErrorReportAggregation request) {
        return GetSummaryStatisticsReportResponse.from(service.getSummaryStatistics(request));
    }

}
