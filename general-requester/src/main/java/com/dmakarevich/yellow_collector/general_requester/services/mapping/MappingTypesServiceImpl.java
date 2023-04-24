package com.dmakarevich.yellow_collector.general_requester.services.mapping;

import com.dmakarevich.yellow_collector.general_requester.view.responses.errors.GetErrorReportHeadersResponse;
import com.dmakarevich.yellow_collector.general_requester.view.responses.mapping.MappingTypeDeclaration;
import com.dmakarevich.yellow_collector.general_requester.view.responses.statistics.GetSummaryStatisticsReportResponse;
import org.springframework.stereotype.Service;

@Service
public class MappingTypesServiceImpl implements MappingTypesService {

    @Override
    public MappingTypeDeclaration getErrorHeadersMapping() {
        return GetErrorReportHeadersResponse.getMappingType();
    }

    @Override
    public MappingTypeDeclaration getSummaryStatisticsReportMapping() {
        return GetSummaryStatisticsReportResponse.getMappingType();
    }
}
