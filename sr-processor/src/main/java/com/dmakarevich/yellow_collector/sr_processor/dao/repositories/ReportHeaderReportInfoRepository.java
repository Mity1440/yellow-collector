package com.dmakarevich.yellow_collector.sr_processor.dao.repositories;

import com.dmakarevich.yellow_collector.sr_processor.dao.model.header.ReportHeader;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ReportHeaderReportInfoRepository extends ElasticsearchRepository<ReportHeader, String> {
}
