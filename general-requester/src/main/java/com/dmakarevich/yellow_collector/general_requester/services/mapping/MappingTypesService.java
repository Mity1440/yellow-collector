package com.dmakarevich.yellow_collector.general_requester.services.mapping;

import com.dmakarevich.yellow_collector.general_requester.view.responses.mapping.MappingTypeDeclaration;

public interface MappingTypesService {

    MappingTypeDeclaration getErrorHeadersMapping();

    MappingTypeDeclaration getSummaryStatisticsReportMapping();

}
