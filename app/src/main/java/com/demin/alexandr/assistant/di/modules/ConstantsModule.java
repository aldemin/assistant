package com.demin.alexandr.assistant.di.modules;

import com.demin.alexandr.assistant.mvp.model.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class ConstantsModule {

    @Provides
    @Singleton
    public Constants getConstants() {
        return new Constants();
    }

}
