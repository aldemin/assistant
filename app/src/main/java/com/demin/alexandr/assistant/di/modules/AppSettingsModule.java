package com.demin.alexandr.assistant.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AppSettingsModule {

    private SharedPreferences sharedPreferences;

    public AppSettingsModule(Context context) {
        String sharedPreferencesName = "AppSettings";
        this.sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
    }

    @Provides
    public SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    @Provides
    @Named("Remember me")
    public String rememberMeTag() {
        return "Remember me";
    }

    @Provides
    @Named("isFirstRun")
    public String isFirstRunTag() {
        return "First Run";
    }

    @Provides
    @Named("Email")
    public String emailTag() {
        return "Email";
    }



}
