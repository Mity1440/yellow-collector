package com.dmakarevich.yellow_collector.db.repositories;

import com.dmakarevich.yellow_collector.db.model.info.ErrorInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorInfoReportRepository extends MongoRepository<ErrorInfo, String> {
}
