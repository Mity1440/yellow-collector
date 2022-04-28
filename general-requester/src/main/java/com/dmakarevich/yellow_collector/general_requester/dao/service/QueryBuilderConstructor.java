package com.dmakarevich.yellow_collector.general_requester.dao.service;

import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorHeadersRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class QueryBuilderConstructor {

    static QueryBuilder getQueryBuilderFromHeaderRequest(GetErrorHeadersRequest request) {

        QueryBuilder queryBuilder =
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.rangeQuery("time")
                                .gte(request.getFrom())
                                .lte(request.getTo()));

        return queryBuilder;

    }
}
