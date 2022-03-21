package com.dmakarevich.yellow_collector.db.repositories;

import com.dmakarevich.yellow_collector.db.model.info.ErrorInfoDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorInfoDetailRepository extends MongoRepository<ErrorInfoDetail, String> {
}
