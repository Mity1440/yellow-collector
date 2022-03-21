package com.dmakarevich.yellow_collector.db.services;

import com.dmakarevich.yellow_collector.db.model.info.ErrorInfoDetail;
import com.dmakarevich.yellow_collector.db.model.info.ErrorInfoStackDetail;
import com.dmakarevich.yellow_collector.db.repositories.BasicErrorReportInfoRepository;
import com.dmakarevich.yellow_collector.db.repositories.ErrorInfoDetailRepository;
import com.dmakarevich.yellow_collector.db.repositories.ErrorInfoReportRepository;
import com.dmakarevich.yellow_collector.db.repositories.ErrorInfoStackDetailRepository;
import com.dmakarevich.yellow_collector.db.services.conventers.ReportInfoConverter;
import com.dmakarevich.yellow_collector.services.report.model.file.FileReportErrorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportProcessorDBServiceImpl implements ReportProcessorDBService{

    private final BasicErrorReportInfoRepository basicErrorReportInfoRepository;
    private final ErrorInfoReportRepository errorInfoReportRepository;
    private final ErrorInfoDetailRepository errorInfoDetailRepository;
    private final ErrorInfoStackDetailRepository errorInfoStackDetailRepository;
    private final ReportInfoConverter converter;

    @Autowired
    public ReportProcessorDBServiceImpl(BasicErrorReportInfoRepository repository,
                                        ErrorInfoReportRepository errorInfoReportRepository,
                                        ErrorInfoDetailRepository errorInfoDetailRepository,
                                        ErrorInfoStackDetailRepository errorInfoStackDetailRepository,
                                        ReportInfoConverter converter) {
        this.basicErrorReportInfoRepository = repository;
        this.errorInfoReportRepository = errorInfoReportRepository;
        this.errorInfoDetailRepository = errorInfoDetailRepository;
        this.errorInfoStackDetailRepository = errorInfoStackDetailRepository;
        this.converter = converter;
    }

    @Override
    //@Transactional
    public void save(FileReportErrorInfo fileErrorInfo) {

        var baseErrorInfo = converter.fromFileReportErrorInfoToBasicErrorReportInfo(fileErrorInfo);

        var errorInfo = converter.fromFileReportErrorInfoToErrorInfo(fileErrorInfo);

        List<ErrorInfoDetail> errorDetails = converter.fromFileReportErrorToErrorInfoDetails(fileErrorInfo);
        errorDetails.forEach(errorInfoDetail -> errorInfoDetail.setErrorInfoId(errorInfo.getId()));
        if (errorInfo.getApplicationErrorInfo() != null &&
                errorInfo.getApplicationErrorInfo().getErrors() != null){
            var errors = errorInfo.getApplicationErrorInfo().getErrors();
            errorDetails.forEach(errorInfoDetail -> errors.add(errorInfoDetail.getId()));
        }

        List<ErrorInfoStackDetail> errorStack = converter.fromFileReportErrorToErrorStack(fileErrorInfo);
        errorStack.forEach(errorStackElement -> errorStackElement.setErrorInfoId(errorInfo.getId()));
        if (errorInfo.getApplicationErrorInfo() != null &&
                errorInfo.getApplicationErrorInfo().getStack() != null){
            var stack = errorInfo.getApplicationErrorInfo().getStack();
            errorStack.forEach(errorStackElement -> stack.add(errorStackElement.getId()));
        }

        basicErrorReportInfoRepository.save(baseErrorInfo);
        errorInfoReportRepository.save(errorInfo);
        errorDetails.forEach(errorInfoDetailRepository::save);
        errorStack.forEach(errorInfoStackDetailRepository::save);

    }



}
