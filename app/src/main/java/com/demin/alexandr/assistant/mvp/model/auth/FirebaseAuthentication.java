package com.demin.alexandr.assistant.mvp.model.auth;

import android.app.Activity;
import android.content.Context;

import com.demin.alexandr.assistant.mvp.model.entity.User;
import com.demin.alexandr.assistant.mvp.model.parser.FirebaseUserParser;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.Completable;
import io.reactivex.Single;

public class FirebaseAuthentication implements Authentication {

    private FirebaseAuth firebaseAuth;
    private FirebaseUserParser parser;

    public FirebaseAuthentication(FirebaseAuth firebaseAuth, FirebaseUserParser parser) {
        this.firebaseAuth = firebaseAuth;
        this.parser = parser;
    }

    @Override
    public Single<User> getCurrentUser() {
        return Single.create(emitter -> {
            if (firebaseAuth.getCurrentUser() == null || firebaseAuth.getCurrentUser().getUid() == null) {
                // TODO: 24.03.2019 add error text
                emitter.onError(new RuntimeException());
            }
            emitter.onSuccess(parser.parse(firebaseAuth.getCurrentUser()));
        });
    }

    @Override
    public Completable deleteUser() {
        return Completable.create(emitter -> {
            if (firebaseAuth.getCurrentUser() != null && firebaseAuth.getCurrentUser().getUid() != null) {
                firebaseAuth.getCurrentUser().delete().addOnCompleteListener(task -> emitter.onComplete());
            }
        });
    }

    @Override
    public Single<String> singIn(Context context, String email, String password) {
        return Single.create(emitter -> {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, task -> {
                        if (task.isSuccessful()) {
                            emitter.onSuccess(task.getResult().getUser().getUid());
                        } else {
                            emitter.onError(task.getException());
                        }
                    });

        });
    }

    @Override
    public Completable singOut() {
        return Completable.create(emitter -> {
            firebaseAuth.signOut();
            emitter.onComplete();
        });
    }

    @Override
    public Completable registration(Context context, String email, String password) {
        return Completable.create(emitter -> {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, task -> {
                        if (task.isSuccessful()) {
                            emitter.onComplete();
                        } else {
                            emitter.onError(task.getException());
                        }
                    });
        });
    }
}
