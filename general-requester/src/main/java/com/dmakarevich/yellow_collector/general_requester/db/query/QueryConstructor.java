package com.dmakarevich.yellow_collector.general_requester.db.query;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import java.util.Date;

public class QueryConstructor {

     public Query getQueryForGetReportHeaderRequest(Date from, Date to) {

         var builder = QueryBuilders.boolQuery()
            .must(QueryBuilders.rangeQuery("time")
            .gte(from)
            .lte(to));

         return new NativeSearchQueryBuilder().withQuery(builder).build();

    }

    public Query getQueryForReportHeaderById(String id) {
        return new NativeSearchQueryBuilder().withQuery(QueryBuilders.idsQuery().addIds(id)).build();
    }

    public Query getQueryForErrorInfoByReportId(String reportId){
        return baseBuilderByReportId(reportId).build();
    }

    public Query getQueryForErrorDetailsByReportId(String reportId) {
        return baseBuilderByReportId(reportId).build();
    }

    public Query getQueryForErrorStackByReportId(String reportId) {
        return baseBuilderByReportId(reportId).build();
    }

    private NativeSearchQueryBuilder baseBuilderByReportId(String reportId){
        return new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("reportId", reportId)));
    }

}
