package com.dmakarevich.yellow_collector.general_requester.db.service;

import com.dmakarevich.yellow_collector.general_requester.db.model.statistic.GetSummaryDBStatisticsReportResponseElement;
import com.dmakarevich.yellow_collector.general_requester.db.query.QueryConstructor;
import com.dmakarevich.yellow_collector.general_requester.view.requests.GetBaseErrorReportAggregation;
import com.dmakarevich.yellow_collector.general_requester.db.model.statistic.GetSummaryDBStatisticsReportResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;

import lombok.RequiredArgsConstructor;


import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ReportsAggregationDBService {

    private final RestHighLevelClient elOps;
    private final QueryConstructor constructor;

    public GetSummaryDBStatisticsReportResponse getSummaryStatistics(GetBaseErrorReportAggregation request){

        final TermsAggregationBuilder aggregation = constructor.builderForSummaryStatistics();
        final SearchSourceBuilder builder = new SearchSourceBuilder().aggregation(aggregation);
        final SearchRequest searchRequest = Requests.searchRequest("report-headers-*").source(builder);

        final ArrayList<GetSummaryDBStatisticsReportResponseElement> statistics = new ArrayList<>();

        try {
            final SearchResponse response = elOps.search(searchRequest, RequestOptions.DEFAULT);
            final Aggregations aggs = response.getAggregations();
            ParsedStringTerms baseAggTerms = aggs.get("baseAgg");
            for (var baseAggBucket: baseAggTerms.getBuckets()){
                String baseName = (String) baseAggBucket.getKey();
                long baseNameDocCount = baseAggBucket.getDocCount();
                ParsedStringTerms appNameAggTerms = baseAggBucket.getAggregations().get("appNameAgg");
                for (var appNameBucket: appNameAggTerms.getBuckets()){
                    String appName = (String)appNameBucket.getKey();
                    long appNameDocCount = appNameBucket.getDocCount();
                    ParsedStringTerms contextErrorTerms = appNameBucket.getAggregations().get("contextErrorAgg");
                    for (var contextErrorBucket: contextErrorTerms.getBuckets()){
                        String contextName = (String)contextErrorBucket.getKey();
                        long contextErrorCount = contextErrorBucket.getDocCount();

                        statistics.add(
                                GetSummaryDBStatisticsReportResponseElement.of(baseName,
                                                                             appName,
                                                                             contextName,
                                                                             contextErrorCount));

                    }
                }
            }

            return GetSummaryDBStatisticsReportResponse.of(statistics);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
