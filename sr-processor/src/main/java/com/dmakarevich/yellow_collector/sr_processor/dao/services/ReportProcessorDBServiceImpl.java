package com.dmakarevich.yellow_collector.sr_processor.dao.services;

import com.dmakarevich.yellow_collector.sr_processor.dao.model.ReportErrorInfo;
import com.dmakarevich.yellow_collector.sr_processor.dao.repositories.*;

import com.dmakarevich.yellow_collector.sr_processor.report.model.file.FileReportErrorInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportProcessorDBServiceImpl implements ReportProcessorDBService{

    private final ReportHeaderReportInfoRepository reportHeaderReportInfoRepository;
    private final ErrorInfoReportRepository errorInfoReportRepository;
    private final ErrorInfoDetailRepository errorInfoDetailRepository;
    private final ErrorInfoStackDetailRepository errorInfoStackDetailRepository;

    @Autowired
    public ReportProcessorDBServiceImpl(ReportHeaderReportInfoRepository repository,
                                        ErrorInfoReportRepository errorInfoReportRepository,
                                        ErrorInfoDetailRepository errorInfoDetailRepository,
                                        ErrorInfoStackDetailRepository errorInfoStackDetailRepository) {
        this.reportHeaderReportInfoRepository = repository;
        this.errorInfoReportRepository = errorInfoReportRepository;
        this.errorInfoDetailRepository = errorInfoDetailRepository;
        this.errorInfoStackDetailRepository = errorInfoStackDetailRepository;
    }

    @Override
    public void save(FileReportErrorInfo fileErrorInfo) {
        save(fileErrorInfo.toModel());
    }

    public void save(ReportErrorInfo reportErrorInfo) {

        reportHeaderReportInfoRepository.save(reportErrorInfo.getHeader());
        errorInfoReportRepository.save(reportErrorInfo.getErrorInfo());

        for (var errorDetail: reportErrorInfo.getErrorDetails()){
            errorInfoDetailRepository.save(errorDetail);
        }

        for (var errorStackElement: reportErrorInfo.getErrorStack()){
            errorInfoStackDetailRepository.save(errorStackElement);
        }

    }

}
