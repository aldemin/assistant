package com.demin.alexandr.assistant.di.modules;

import com.demin.alexandr.assistant.mvp.model.auth.Authentication;
import com.demin.alexandr.assistant.mvp.model.auth.FirebaseAuthentication;
import com.demin.alexandr.assistant.mvp.model.parser.FirebaseUserParser;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AuthModule {

    @Provides
    @Singleton
    public Authentication firebaseAuthentification(FirebaseAuth firebaseAuth, FirebaseUserParser parser) {
        return new FirebaseAuthentication(firebaseAuth, parser);
    }
}
