package com.dmakarevich.yellow_collector.sr_processor.dao.repositories;

import com.dmakarevich.yellow_collector.sr_processor.dao.model.details.ErrorInfoDetail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ErrorInfoDetailRepository extends ElasticsearchRepository<ErrorInfoDetail, String> {
}
