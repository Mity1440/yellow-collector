package com.dmakarevich.yellow_collector.general_requester.view.responses.errors;

import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.base.ErrorReportHeader;
import com.dmakarevich.yellow_collector.general_requester.view.responses.mapping.FieldTypeDeclaration;
import com.dmakarevich.yellow_collector.general_requester.view.responses.mapping.MappingTypeDeclaration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetErrorReportHeadersResponse {

    private List<ErrorReportHeader> headers;

    public static GetErrorReportHeadersResponse fromErrorReportHeaders(List<ErrorReportHeader> reportList) {
        return new GetErrorReportHeadersResponse(reportList);
    }

    public static MappingTypeDeclaration getMappingType() {

        return MappingTypeDeclaration
                .getFromFields(
                        Arrays.stream(ErrorReportHeader.class
                                            .getDeclaredFields())
                                            .map(FieldTypeDeclaration::getFromReflectField)
                                            .collect(Collectors.toList()));

    }
}
