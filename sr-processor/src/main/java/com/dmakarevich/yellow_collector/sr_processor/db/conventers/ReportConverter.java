package com.dmakarevich.yellow_collector.sr_processor.db.conventers;

import com.dmakarevich.yellow_collector.sr_processor.db.model.details.*;
import com.dmakarevich.yellow_collector.sr_processor.db.model.header.*;
import com.dmakarevich.yellow_collector.sr_processor.report.model.file.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ReportConverter {

    public static ReportHeader fromFileReportErrorInfoToReportHeader(FileReportErrorInfo fileReportInfo) {

        if(fileReportInfo == null){
            return null;
        }

        var clientInfo = getClientInfoFromFileClientInfo(fileReportInfo.getClientInfo());
        var sessionInfo = getSessionInfoFromFileSessionInfo(fileReportInfo.getSessionInfo());
        var infoBaseInfo = getInfoBaseInfoFromFileInfoBaseInfo(fileReportInfo.getInfoBaseInfo());
        var serverInfo = getServerInfoFromFileServerInfo(fileReportInfo.getServerInfo());
        var configInfo = getConfigInfoFromFileConfigInfo(fileReportInfo.getConfigInfo());

        return ReportHeader
                .builder()
                .id(fileReportInfo.getId())
                .time(fileReportInfo.getTime())
                .clientInfo(clientInfo)
                .sessionInfo(sessionInfo)
                .infoBaseInfo(infoBaseInfo)
                .serverInfo(serverInfo)
                .configInfo(configInfo)
                .baseName(fileReportInfo.getBaseName())
                .group(fileReportInfo.getGroup())
                .deletedMark(false)
                .build();

    }

    public static ErrorInfo fromFileReportErrorInfoToErrorInfo(FileReportErrorInfo fileReportInfo) {

        if (fileReportInfo == null || fileReportInfo.getErrorInfo() == null) {
            return null;
        }

        var errorInfo = fileReportInfo.getErrorInfo();

        SystemErrorInfo systemErrorInfo = getSystemErrorInfoFromFileSystemErrorInfo(errorInfo.getSystemErrorInfo());
        ApplicationErrorInfo applicationErrorInfo = getApplicationErrorInfoFromFileApplicationErrorInfo(errorInfo.getApplicationErrorInfo());

        return  ErrorInfo
                .builder()
                .applicationErrorInfo(applicationErrorInfo)
                .systemErrorInfo(systemErrorInfo)
                .userDescription(errorInfo.getUserDescription())
                .reportId(fileReportInfo.getId())
                .id(UUID.randomUUID().toString())
                .build();

    }

    public static List<ErrorInfoDetail> fromFileReportErrorToErrorInfoDetails(FileReportErrorInfo fileReportInfo) {

        var result = new ArrayList<ErrorInfoDetail>();
        if (fileReportInfo == null
                || fileReportInfo.getErrorInfo() == null
                || fileReportInfo.getErrorInfo().getApplicationErrorInfo() == null
                || fileReportInfo.getErrorInfo().getApplicationErrorInfo().getErrors() == null){
            return result;
        }

        var errorList = fileReportInfo.getErrorInfo().getApplicationErrorInfo().getErrors();
        errorList.getErrors().forEach(error ->{
            var errorInfoDetail = getErrorInfoDetailFromFileApplicationErrorInfoErrorsElement(error);
            errorInfoDetail.setReportId(fileReportInfo.getId());
            result.add(errorInfoDetail);
        });

        return result;

    }

    public static List<ErrorInfoStackDetail> fromFileReportErrorToErrorStack(FileReportErrorInfo fileReportInfo) {

        var result = new ArrayList<ErrorInfoStackDetail>();
        if (fileReportInfo == null
                || fileReportInfo.getErrorInfo() == null
                || fileReportInfo.getErrorInfo().getApplicationErrorInfo() == null
                || fileReportInfo.getErrorInfo().getApplicationErrorInfo().getStack() == null){
            return result;
        }

        var stackList = fileReportInfo.getErrorInfo().getApplicationErrorInfo().getStack();
        stackList.forEach(stackElem ->{
            var stackDetailInfo = getStackDetailInfoFromFileStackElement(stackElem);
            stackDetailInfo.setReportId(fileReportInfo.getId());
            result.add(stackDetailInfo);
        });

        return result;

    }

    private static ErrorInfoStackDetail getStackDetailInfoFromFileStackElement(List<String> stackElemet) {

        return ErrorInfoStackDetail
                .builder()
                .id(UUID.randomUUID().toString())
                .moduleName(stackElemet.get(0))
                .lineNumber(Integer.parseInt(stackElemet.get(1)))
                .sourceModuleText(stackElemet.get(2))
                .build();

    }

    private static ErrorInfoDetail getErrorInfoDetailFromFileApplicationErrorInfoErrorsElement(FileApplicationErrorInfoErrorsElement fileErrorElement) {

        return ErrorInfoDetail
                .builder()
                .id(UUID.randomUUID().toString())
                .messageText(fileErrorElement.getErrorDescription())
                .categories(new ArrayList<>(fileErrorElement.getErrorCategories()))
                .build();

    }

    private static ApplicationErrorInfo getApplicationErrorInfoFromFileApplicationErrorInfo(FileApplicationErrorInfo fileApplicationErrorInfo) {

        if (fileApplicationErrorInfo == null){
            return null;
        }

        return ApplicationErrorInfo
                .builder()
                .stackHash(fileApplicationErrorInfo.getStackHash())
                .errors(new ArrayList<>())
                .stack(new ArrayList<>())
                .build();

    }

    private static SystemErrorInfo getSystemErrorInfoFromFileSystemErrorInfo(FileSystemErrorInfo fileErrorInfo) {

        if (fileErrorInfo == null){
            return null;
        }

        return SystemErrorInfo
                .builder()
                .clientStack(fileErrorInfo.getClientStack())
                .clientStackHash(fileErrorInfo.getClientStackHash())
                .serverStack(fileErrorInfo.getServerStack())
                .serverStackHash(fileErrorInfo.getServerStackHash())
                .build();

    }

    private static ConfigInfo getConfigInfoFromFileConfigInfo(FileConfigInfo fileConfigInfo) {

        if (fileConfigInfo == null){
            return null;
        }

        var configInfo = ConfigInfo
                .builder()
                .changeEnabled(fileConfigInfo.getChangeEnabled())
                .compatibilityMode(fileConfigInfo.getCompatibilityMode())
                .description(fileConfigInfo.getDescription())
                .hash(fileConfigInfo.getHash())
                .name(fileConfigInfo.getName())
                .version(fileConfigInfo.getVersion())
                .build();

        return configInfo;

    }

    private static ServerInfo getServerInfoFromFileServerInfo(FileServerInfo fileServerInfo) {

        if (fileServerInfo == null){
            return null;
        }

        var serverInfo = ServerInfo
                .builder()
                .dbms(fileServerInfo.getDbms())
                .appVersion(fileServerInfo.getAppVersion())
                .type(fileServerInfo.getType())
                .build();

        return serverInfo;

    }

    private static InfoBaseInfo getInfoBaseInfoFromFileInfoBaseInfo(FileInfoBaseInfo fileInfoBaseInfo) {

        if (fileInfoBaseInfo == null){
            return null;
        }

        var infoBaseInfo = InfoBaseInfo
                .builder()
                .localeCode(fileInfoBaseInfo.getLocaleCode())
                .build();

        return infoBaseInfo;

    }

    private static SessionInfo getSessionInfoFromFileSessionInfo(FileSessionInfo fileSessionInfo) {

        if (fileSessionInfo == null){
            return null;
        }

        var sessionInfo = SessionInfo
                .builder()
                .configurationInterfaceLanguageCode(fileSessionInfo.getConfigurationInterfaceLanguageCode())
                .dataSeparation(fileSessionInfo.getDataSeparation())
                .localeCode(fileSessionInfo.getLocaleCode())
                .platformInterfaceLanguageCode(fileSessionInfo.getPlatformInterfaceLanguageCode())
                .userName(fileSessionInfo.getUserName())
                .build();

        return sessionInfo;

    }

    private static ClientInfo getClientInfoFromFileClientInfo(FileClientInfo fileClientInfo) {

        if (fileClientInfo == null) {
            return null;
        }

        SystemInfo systemInfo = getSystemInfoFromFileSystemInfo(fileClientInfo.getSystemInfo());

        var clientInfo = ClientInfo
                .builder()
                .platformType(fileClientInfo.getPlatformType())
                .appName(fileClientInfo.getAppName())
                .appVersion(fileClientInfo.getAppVersion())
                .systemInfo(systemInfo)
                .build();

        return clientInfo;


    }

    private static SystemInfo getSystemInfoFromFileSystemInfo(FileSystemInfo fileSystemInfo) {

        if (fileSystemInfo == null) {
            return null;
        }

        var systemInfo = SystemInfo
                    .builder()
                    .freeRAM(fileSystemInfo.getFreeRAM())
                    .clientID(fileSystemInfo.getClientID())
                    .fullRAM(fileSystemInfo.getFullRAM())
                    .osVersion(fileSystemInfo.getOsVersion())
                    .processor(fileSystemInfo.getProcessor())
                    .build();

        return systemInfo;

    }

}
