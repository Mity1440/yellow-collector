package com.dmakarevich.yellow_collector.db.repositories;

import com.dmakarevich.yellow_collector.db.services.model.ErrorInfoSettings;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ErrorInfoSettingsRepository extends MongoRepository<ErrorInfoSettings, String> {

    ErrorInfoSettings findByConfigNameAndServerStackHashAndClientStackHash(String configName,
                                                                           String serverStackHash,
                                                                           String clientStackHash);
    List<ErrorInfoSettings> findByConfigName(String configName);

}
