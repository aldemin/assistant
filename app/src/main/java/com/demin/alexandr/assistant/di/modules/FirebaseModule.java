package com.demin.alexandr.assistant.di.modules;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class FirebaseModule {

    @Provides
    @Singleton
    public FirebaseAuth getAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    public FirebaseFirestore firestore() {
        return FirebaseFirestore.getInstance();
    }

}
