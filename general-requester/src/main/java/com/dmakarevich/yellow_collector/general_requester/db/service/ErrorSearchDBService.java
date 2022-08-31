package com.dmakarevich.yellow_collector.general_requester.db.service;

import com.dmakarevich.yellow_collector.general_requester.db.model.ReportInfoDetail;
import com.dmakarevich.yellow_collector.general_requester.db.model.details.ErrorInfo;
import com.dmakarevich.yellow_collector.general_requester.db.model.details.ErrorInfoDetail;
import com.dmakarevich.yellow_collector.general_requester.db.model.details.ErrorInfoStackDetail;
import com.dmakarevich.yellow_collector.general_requester.db.model.header.ReportHeader;
import com.dmakarevich.yellow_collector.general_requester.db.query.QueryConstructor;
import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorReportHeadersRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;

import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ErrorSearchDBService {

    private final ElasticsearchOperations elOps;
    private final QueryConstructor constructor;

    public ErrorSearchDBService(ElasticsearchOperations elOps) {
        this.elOps = elOps;
        this.constructor = new QueryConstructor();
    }

    public List<ReportHeader> getReportHeaders(GetErrorReportHeadersRequest request) {
        return elOps
                .search(constructor.getQueryForGetReportHeaderRequest(request.getFrom(), request.getTo()),
                          ReportHeader.class,
                          IndexCoordinates.of("report-headers-*"))
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public ReportInfoDetail getErrorDetails(String id){
        return ReportInfoDetail.builder()
                .header(getReportHeaderById(id))
                .errorInfo(getErrorInfoByReportId(id))
                .errorDetails(getErrorDetailsByReportId(id))
                .errorStack(getErrorStackByReportId(id))
                .build();
    }

    private ReportHeader getReportHeaderById(String id){
        return elOps
                .search(constructor.getQueryForReportHeaderById(id),
                        ReportHeader.class,
                        IndexCoordinates.of("report-headers-*"))
                .stream()
                .findFirst().orElseThrow().getContent();
    }

    private ErrorInfo getErrorInfoByReportId(String reportId){
        return elOps
                .search(constructor.getQueryForErrorInfoByReportId(reportId),
                        ErrorInfo.class,
                        IndexCoordinates.of("error-info-*"))
                .stream()
                .findFirst().orElseThrow().getContent();
    }

    private List<ErrorInfoDetail> getErrorDetailsByReportId(String reportId) {
        return elOps
                .search(constructor.getQueryForErrorDetailsByReportId(reportId),
                        ErrorInfoDetail.class,
                        IndexCoordinates.of("error-details-*"))
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    private List<ErrorInfoStackDetail> getErrorStackByReportId(String reportId) {
        return elOps
                .search(constructor.getQueryForErrorStackByReportId(reportId),
                        ErrorInfoStackDetail.class,
                        IndexCoordinates.of("error-stack-*"))
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

}
