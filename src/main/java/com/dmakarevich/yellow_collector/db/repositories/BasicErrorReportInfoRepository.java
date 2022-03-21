package com.dmakarevich.yellow_collector.db.repositories;

import com.dmakarevich.yellow_collector.db.model.main.BasicErrorReportInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasicErrorReportInfoRepository extends MongoRepository<BasicErrorReportInfo, String> {
}
