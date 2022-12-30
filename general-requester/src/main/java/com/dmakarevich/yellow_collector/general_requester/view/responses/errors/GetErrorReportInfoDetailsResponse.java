package com.dmakarevich.yellow_collector.general_requester.view.responses.errors;

import com.dmakarevich.yellow_collector.general_requester.db.model.ReportInfoDetail;
import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.base.ErrorReportDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GetErrorReportInfoDetailsResponse {

    private List<ErrorReportDetail> details;

    public static GetErrorReportInfoDetailsResponse fromErrorDetails(ReportInfoDetail errorDetails) {
        return new GetErrorReportInfoDetailsResponse(
                List.of(ErrorReportDetail.fromModel(errorDetails)));
    }
}
