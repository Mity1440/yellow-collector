
package com.dmakarevich.yellow_collector.general_requester.db.model.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemErrorInfo {

    private String clientStack;
    private String clientStackHash;
    private String serverStack;
    private String serverStackHash;
    private boolean systemCrash;

}
