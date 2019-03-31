package com.demin.alexandr.assistant.di.modules;

import com.demin.alexandr.assistant.mvp.model.parser.FirebaseDocumentsParser;
import com.demin.alexandr.assistant.mvp.model.repository.FirebaseRepository;
import com.demin.alexandr.assistant.mvp.model.repository.Repository;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class RepositoriesModule {

    @Provides
    @Singleton
    @Named("firebase")
    public Repository firebaseRepository(FirebaseFirestore firebaseFirestore, FirebaseDocumentsParser parser) {
        return new FirebaseRepository(firebaseFirestore, parser);
    }

}
