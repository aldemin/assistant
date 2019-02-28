package com.demin.alexandr.assistant.di.modules;

import com.demin.alexandr.assistant.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public App getApp() {
        return app;
    }
}
