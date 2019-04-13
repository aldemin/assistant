package com.demin.alexandr.assistant.di.modules;

import com.demin.alexandr.assistant.mvp.model.parser.DateParser;
import com.demin.alexandr.assistant.mvp.model.parser.FirebaseDocumentsParser;
import com.demin.alexandr.assistant.mvp.model.parser.FirebaseUserParser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class ParserModule {

    @Provides
    @Singleton
    public FirebaseDocumentsParser firebaseDocumentsParser() {
        return new FirebaseDocumentsParser();
    }

    @Provides
    @Singleton
    public FirebaseUserParser firebaseUserParser() {
        return new FirebaseUserParser();
    }

    @Provides
    @Singleton
    public DateParser dateParser() {
        return new DateParser();
    }

}
