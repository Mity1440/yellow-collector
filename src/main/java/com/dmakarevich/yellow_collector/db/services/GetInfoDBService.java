package com.dmakarevich.yellow_collector.db.services;

import com.dmakarevich.yellow_collector.db.services.model.ErrorInfoSettings;
import com.dmakarevich.yellow_collector.db.services.model.ErrorInfoRequest;

public interface GetInfoDBService {

    ErrorInfoSettings get(ErrorInfoRequest request);

}
