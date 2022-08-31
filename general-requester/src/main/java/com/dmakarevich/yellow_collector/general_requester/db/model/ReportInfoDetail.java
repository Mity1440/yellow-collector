package com.dmakarevich.yellow_collector.general_requester.db.model;

import com.dmakarevich.yellow_collector.general_requester.db.model.details.ErrorInfo;
import com.dmakarevich.yellow_collector.general_requester.db.model.details.ErrorInfoDetail;
import com.dmakarevich.yellow_collector.general_requester.db.model.details.ErrorInfoStackDetail;
import com.dmakarevich.yellow_collector.general_requester.db.model.header.ReportHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ReportInfoDetail {

    private final ReportHeader header;
    private final ErrorInfo errorInfo;
    private final List<ErrorInfoDetail> errorDetails;
    private final List<ErrorInfoStackDetail> errorStack;

}
