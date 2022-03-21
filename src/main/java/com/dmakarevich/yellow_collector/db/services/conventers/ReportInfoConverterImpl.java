package com.dmakarevich.yellow_collector.db.services.conventers;

import com.dmakarevich.yellow_collector.db.model.info.*;
import com.dmakarevich.yellow_collector.db.model.main.*;
import com.dmakarevich.yellow_collector.services.report.model.file.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Data
public class ReportInfoConverterImpl implements ReportInfoConverter<FileReportErrorInfo> {

    @Override
    public BasicErrorReportInfo fromFileReportErrorInfoToBasicErrorReportInfo(FileReportErrorInfo fileReportInfo) {

        if(fileReportInfo == null){
            return null;
        }

        ClientInfo clientInfo = getClientInfoFromFileClientInfo(fileReportInfo.getClientInfo());
        SessionInfo sessionInfo = getSessionInfoFromFileSessionInfo(fileReportInfo.getSessionInfo());
        InfoBaseInfo infoBaseInfo = getInfoBaseInfoFromFileInfoBaseInfo(fileReportInfo.getInfoBaseInfo());
        ServerInfo serverInfo = getServerInfoFromFileServerInfo(fileReportInfo.getServerInfo());
        ConfigInfo configInfo = getConfigInfoFromFileConfigInfo(fileReportInfo.getConfigInfo());

        return BasicErrorReportInfo
                .builder()
                .id(fileReportInfo.getId())
                .time(fileReportInfo.getTime())
                .clientInfo(clientInfo)
                .sessionInfo(sessionInfo)
                .infoBaseInfo(infoBaseInfo)
                .serverInfo(serverInfo)
                .configInfo(configInfo)
                .build();

    }

    @Override
    public ErrorInfo fromFileReportErrorInfoToErrorInfo(FileReportErrorInfo fileReportInfo) {

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
                .reportId(fileReportInfo.getId())
                .id(UUID.randomUUID().toString())
                .build();

    }

    @Override
    public List<ErrorInfoDetail> fromFileReportErrorToErrorInfoDetails(FileReportErrorInfo fileReportInfo) {

        var result = new ArrayList<ErrorInfoDetail>();
        if (fileReportInfo == null
                || fileReportInfo.getErrorInfo() == null
                || fileReportInfo.getErrorInfo().getApplicationErrorInfo() == null
                || fileReportInfo.getErrorInfo().getApplicationErrorInfo().getErrors() == null){
            return result;
        }

        var errorList = fileReportInfo.getErrorInfo().getApplicationErrorInfo().getErrors();
        errorList.forEach(error ->{
            var errorInfoDetail = getErrorInfoDetailFromFileApplicationErrorInfoErrorsElement(error);
            errorInfoDetail.setReportId(fileReportInfo.getId());
            result.add(errorInfoDetail);
        });

        return result;

    }

    @Override
    public List<ErrorInfoStackDetail> fromFileReportErrorToErrorStack(FileReportErrorInfo fileReportInfo) {

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

    private ErrorInfoStackDetail getStackDetailInfoFromFileStackElement(List<String> stackElemet) {

        return ErrorInfoStackDetail
                .builder()
                .id(UUID.randomUUID().toString())
                .moduleName(stackElemet.get(0))
                .lineNumber(Integer.parseInt(stackElemet.get(1)))
                .sourceModuleText(stackElemet.get(2))
                .build();

    }

    private ErrorInfoDetail getErrorInfoDetailFromFileApplicationErrorInfoErrorsElement(FileApplicationErrorInfoErrorsElement fileErrorElement) {

        return ErrorInfoDetail
                .builder()
                .id(UUID.randomUUID().toString())
                .messageText(fileErrorElement.getErrorDescription())
                .categories(new ArrayList<>(fileErrorElement.getErrorCategories()))
                .build();

    }

    private ApplicationErrorInfo getApplicationErrorInfoFromFileApplicationErrorInfo(FileApplicationErrorInfo fileApplicationErrorInfo) {

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

    private SystemErrorInfo getSystemErrorInfoFromFileSystemErrorInfo(FileSystemErrorInfo fileErrorInfo) {

        if (fileErrorInfo == null){
            return null;
        }

        return SystemErrorInfo
                .builder()
                .clientStack(fileErrorInfo.getClientStack())
                .clientStackHash(fileErrorInfo.getClientStackHash())
                .build();

    }

    private ConfigInfo getConfigInfoFromFileConfigInfo(FileConfigInfo fileConfigInfo) {

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

    private ServerInfo getServerInfoFromFileServerInfo(FileServerInfo fileServerInfo) {

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

    private InfoBaseInfo getInfoBaseInfoFromFileInfoBaseInfo(FileInfoBaseInfo fileInfoBaseInfo) {

        if (fileInfoBaseInfo == null){
            return null;
        }

        var infoBaseInfo = InfoBaseInfo
                .builder()
                .localeCode(fileInfoBaseInfo.getLocaleCode())
                .build();

        return infoBaseInfo;

    }

    private SessionInfo getSessionInfoFromFileSessionInfo(FileSessionInfo fileSessionInfo) {

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

    private ClientInfo getClientInfoFromFileClientInfo(FileClientInfo fileClientInfo) {

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

    private SystemInfo getSystemInfoFromFileSystemInfo(FileSystemInfo fileSystemInfo) {

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
