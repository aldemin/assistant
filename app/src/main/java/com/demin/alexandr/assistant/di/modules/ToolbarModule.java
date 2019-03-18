package com.demin.alexandr.assistant.di.modules;

import com.demin.alexandr.assistant.utils.ToolbarManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class ToolbarModule {

    @Provides
    @Singleton
    public ToolbarManager toolbarManager() {
        return new ToolbarManager();
    }

}
