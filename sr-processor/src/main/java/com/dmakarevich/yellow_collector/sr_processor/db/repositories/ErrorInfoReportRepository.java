package com.dmakarevich.yellow_collector.sr_processor.db.repositories;

import com.dmakarevich.yellow_collector.sr_processor.db.model.details.ErrorInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ErrorInfoReportRepository extends ElasticsearchRepository<ErrorInfo, String> {
}
