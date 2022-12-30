package com.dmakarevich.yellow_collector.general_requester.db.service;

import com.dmakarevich.yellow_collector.general_requester.db.query.QueryConstructor;

import lombok.RequiredArgsConstructor;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ScriptType;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;

import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ReportModifyingDBService {

    private final QueryConstructor constructor;
    private final ElasticsearchRestTemplate template;

    public void markForDeletion(String reportID) {

        UpdateQuery query = UpdateQuery
                .builder(constructor.getQueryForReportHeaderById(reportID))
                .withScriptType(ScriptType.INLINE)
                .withLang("painless")
                .withParams(Collections.emptyMap())
                .withScript("ctx._source.deletedMark = !ctx._source.deletedMark;")
                .build();
        template.updateByQuery(query, IndexCoordinates.of("report-headers-*"));

    }
}
