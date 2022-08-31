package com.dmakarevich.yellow_collector.sr_processor.db.repositories;

import com.dmakarevich.yellow_collector.sr_processor.db.model.details.ErrorInfoStackDetail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ErrorInfoStackDetailRepository extends ElasticsearchRepository<ErrorInfoStackDetail, String> {
}
