package com.dmakarevich.yellow_collector.sr_processor.db.services;

import com.dmakarevich.yellow_collector.sr_processor.db.model.ReportErrorInfo;
import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileReportErrorInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

@Component
public class ReportProcessorDBServiceImpl implements ReportProcessorDBService{

    private final ElasticsearchOperations elOps;

    @Autowired
    public ReportProcessorDBServiceImpl(ElasticsearchOperations elOps) {
        this.elOps = elOps;
    }

    @Override
    public void save(FileReportErrorInfo fileErrorInfo) {
        save(fileErrorInfo.toModel());
    }

    public void save(ReportErrorInfo reportErrorInfo) {

        elOps.save(reportErrorInfo.getHeader());
        elOps.save(reportErrorInfo.getErrorInfo());

        for (var errorDetail : reportErrorInfo.getErrorDetails()) {
            elOps.save(errorDetail);
        }

        for (var errorStackElement : reportErrorInfo.getErrorStack()) {
            elOps.save(errorStackElement);
        }

    }

}
