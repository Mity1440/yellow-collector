
package com.dmakarevich.yellow_collector.general_requester.db.model.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationErrorInfo {

    private List<String> errors;
    private List<String> stack;
    private String stackHash;

}
