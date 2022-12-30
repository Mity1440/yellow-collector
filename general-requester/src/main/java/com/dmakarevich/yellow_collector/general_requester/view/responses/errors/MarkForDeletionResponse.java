package com.dmakarevich.yellow_collector.general_requester.view.responses.errors;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class MarkForDeletionResponse {

    private final String result;
    private final List<String> ids;

    public static MarkForDeletionResponse of(String result, String id){
        return new MarkForDeletionResponse(result, List.of(id));
    }

}
