package com.dmakarevich.yellow_collector.db.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfoRequest {

    private String configName;
    private String serverStackHash;
    private String clientStackHash;

}
