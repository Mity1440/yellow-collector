package com.dmakarevich.yellow_collector.general_requester.view.responses.statistics;

import com.dmakarevich.yellow_collector.general_requester.db.model.statistic.GetSummaryDBStatisticsReportResponse;
import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.base.ErrorReportHeader;
import com.dmakarevich.yellow_collector.general_requester.view.responses.mapping.FieldTypeDeclaration;
import com.dmakarevich.yellow_collector.general_requester.view.responses.mapping.MappingTypeDeclaration;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GetSummaryStatisticsReportResponse {

    private final List<GetSummaryStatisticsReportResponseElement> elements;

    private GetSummaryStatisticsReportResponse(List<GetSummaryStatisticsReportResponseElement> elements) {
        this.elements = elements;
    }

    public static GetSummaryStatisticsReportResponse from(GetSummaryDBStatisticsReportResponse model){
        return new GetSummaryStatisticsReportResponse(model.getResult().stream()
                .map(GetSummaryStatisticsReportResponseElement::from)
                .collect(Collectors.toList()));
    }

    public static MappingTypeDeclaration getMappingType() {

        return MappingTypeDeclaration
                .getFromFields(
                        Arrays.stream(GetSummaryStatisticsReportResponseElement.class
                                        .getDeclaredFields())
                                .map(FieldTypeDeclaration::getFromReflectField)
                                .collect(Collectors.toList()));

    }

}
