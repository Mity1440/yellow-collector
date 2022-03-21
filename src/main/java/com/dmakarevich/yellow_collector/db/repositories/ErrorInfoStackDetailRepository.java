package com.dmakarevich.yellow_collector.db.repositories;

import com.dmakarevich.yellow_collector.db.model.info.ErrorInfoStackDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorInfoStackDetailRepository extends MongoRepository<ErrorInfoStackDetail, String> {
}
