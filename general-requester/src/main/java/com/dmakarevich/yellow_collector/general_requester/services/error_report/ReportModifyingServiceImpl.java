package com.dmakarevich.yellow_collector.general_requester.services.error_report;

import com.dmakarevich.yellow_collector.general_requester.db.service.ReportModifyingDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportModifyingServiceImpl implements ReportModifyingService{

    private final ReportModifyingDBService service;

    @Override
    public void markForDeletion(String reportID) {
        service.markForDeletion(reportID);
    }

}
