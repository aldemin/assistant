package com.demin.alexandr.assistant.di.modules;

import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.utils.AppSettings;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AppSettingsModule {

    @Provides
    @Singleton
    public AppSettings appSettings(App context) {
        return new AppSettings(context);
    }

}
