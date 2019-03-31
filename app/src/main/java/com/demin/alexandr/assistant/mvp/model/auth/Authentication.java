package com.demin.alexandr.assistant.mvp.model.auth;

import android.content.Context;

import com.demin.alexandr.assistant.mvp.model.entity.User;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface Authentication {
    Single<User> getCurrentUser();
    Completable deleteUser();
    Completable singIn(Context context, String email, String password);
    Completable singOut();
    Completable registration(Context context, String email, String password);
}
