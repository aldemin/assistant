package com.demin.alexandr.assistant;

import android.app.Application;

import com.demin.alexandr.assistant.di.AppComponent;
import com.demin.alexandr.assistant.di.DaggerAppComponent;
import com.demin.alexandr.assistant.di.modules.AppModule;
import com.demin.alexandr.assistant.di.modules.AppSettingsModule;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import io.paperdb.Paper;

public class App extends Application {
    private static App instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        App.instance = this;

        Paper.init(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .appSettingsModule(new AppSettingsModule(this))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private FirebaseFirestoreSettings initFirestoreSettings() {
        return new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
    }
}
