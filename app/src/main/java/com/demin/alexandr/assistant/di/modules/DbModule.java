package com.demin.alexandr.assistant.di.modules;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class DbModule {

    public DbModule(FirebaseFirestoreSettings settings) {
        FirebaseFirestore.getInstance().setFirestoreSettings(settings);
    }

    @Provides
    public FirebaseFirestore getFirestore() {
        return FirebaseFirestore.getInstance();
    }

}
