package com.demin.alexandr.assistant.di.modules;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AuthModule {

    @Provides
    @Singleton
    public FirebaseAuth getAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    public FirebaseUser getFirebaseUser() {
        return this.getAuth().getCurrentUser();
    }

}
