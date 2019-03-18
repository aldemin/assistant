package com.demin.alexandr.assistant.di.modules;

import com.demin.alexandr.assistant.subnavigation.LocalNavigatorHolder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalNavigationModule {

    @Provides
    @Singleton
    public LocalNavigatorHolder localNavigatorHolder() {
        return new LocalNavigatorHolder();
    }

}
