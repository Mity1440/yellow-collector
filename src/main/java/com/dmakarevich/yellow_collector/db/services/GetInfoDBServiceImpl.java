package com.dmakarevich.yellow_collector.db.services;

import com.dmakarevich.yellow_collector.db.repositories.ErrorInfoSettingsRepository;
import com.dmakarevich.yellow_collector.db.services.model.ErrorInfoSettings;
import com.dmakarevich.yellow_collector.db.services.model.ErrorInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GetInfoDBServiceImpl implements GetInfoDBService{

    private final ErrorInfoSettingsRepository repository;

    @Autowired
    public GetInfoDBServiceImpl(ErrorInfoSettingsRepository repository) {
        this.repository = repository;
    }

    @Override
    public ErrorInfoSettings get(ErrorInfoRequest request) {

        var mainConfigurationSetting =
                repository.findByConfigNameAndServerStackHashAndClientStackHash(request.getConfigName(),"all", "all" );

        var configurationSetting =
                repository.findByConfigNameAndServerStackHashAndClientStackHash(request.getConfigName(),
                                                                                request.getServerStackHash(),
                                                                                request.getClientStackHash());
        return chooseSettingOrGetDefault(mainConfigurationSetting, configurationSetting);
    }

    //region Service

    private ErrorInfoSettings chooseSettingOrGetDefault(ErrorInfoSettings mainConfigurationSetting,
                                                        ErrorInfoSettings configurationSetting) {

        ErrorInfoSettings result = null;
        if (mainConfigurationSetting == null && configurationSetting == null){
            result =  defaultErrorSettings();
        } else if (configurationSetting != null) {
            result = configurationSetting;
        } else {
            result = mainConfigurationSetting;
        }

        return result;

    }

    private ErrorInfoSettings defaultErrorSettings() {
        return ErrorInfoSettings.getDefault();
    }

    //endregion

}
