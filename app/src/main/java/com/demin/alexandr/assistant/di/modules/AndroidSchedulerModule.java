package com.demin.alexandr.assistant.di.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Module
@Singleton
public class AndroidSchedulerModule {

    @Provides
    @Named("mainThread")
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }

}