package com.dmakarevich.yellow_collector.general_requester.db.query;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.springframework.data.elasticsearch.core.query.BaseQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class QueryConstructor {

    public Query getQueryForGetReportHeaderRequest(Date from, Date to, boolean isIncludeDeletedMarkReports) {

         var builder = QueryBuilders.boolQuery()
            .must(QueryBuilders.rangeQuery("time")
            .gte(from)
            .lte(to));

         if (!isIncludeDeletedMarkReports){
             builder.must(QueryBuilders.termQuery("deletedMark", false));
         }

         return new NativeSearchQueryBuilder().withQuery(builder).build();

    }

    public Query getQueryForReportHeaderById(String id) {
        return new NativeSearchQueryBuilder().withQuery(getQueryBuilderForReportHeaderById(id)).build();
    }

    public Query getQueryForErrorInfoByReportId(String reportId){
        return baseNativeSearchQueryBuilderByReportId(reportId).build();
    }

    public Query getQueryForErrorDetailsByReportId(String reportId) {
        return baseNativeSearchQueryBuilderByReportId(reportId).build();
    }

    public Query getQueryForErrorStackByReportId(String reportId) {
        return baseNativeSearchQueryBuilderByReportId(reportId).build();
    }

    public Query getQueryForReportHeadersByIds(Set<String> ids){
        IdsQueryBuilder builder = QueryBuilders.idsQuery();
        ids.forEach(builder::addIds);
        return new NativeSearchQueryBuilder().withQuery(builder).build();

    }

    public BoolQueryBuilder getQueryBuilderByReportId(String reportId) {
        return QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("reportId", reportId));
    }

    private NativeSearchQueryBuilder baseNativeSearchQueryBuilderByReportId(String reportId){
        return new NativeSearchQueryBuilder()
                .withQuery(getQueryBuilderByReportId(reportId));
    }

    private QueryBuilder getQueryBuilderForReportHeaderById(String id) {
        return QueryBuilders.idsQuery().addIds(id);
    }


}
