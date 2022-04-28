package com.dmakarevich.yellow_collector.general_requester.dao.service;

import com.dmakarevich.yellow_collector.general_requester.dao.model.header.ReportHeader;
import com.dmakarevich.yellow_collector.general_requester.view.requests.GetErrorHeadersRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ErrorSearchDBService {

    private final ElasticsearchOperations elasticsearchOperations;

    public ErrorSearchDBService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public List<ReportHeader> getErrorHeaders(GetErrorHeadersRequest request) {

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilderConstructor.getQueryBuilderFromHeaderRequest(request))
                .build();

        SearchHits<ReportHeader> productHits =
                elasticsearchOperations
                        .search(searchQuery,
                                ReportHeader.class,
                                IndexCoordinates.of("report-headers-*"));

        return productHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }


}
