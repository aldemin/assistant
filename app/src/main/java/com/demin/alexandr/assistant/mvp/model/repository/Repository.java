package com.demin.alexandr.assistant.mvp.model.repository;

import com.demin.alexandr.assistant.mvp.model.entity.Pass;
import com.demin.alexandr.assistant.mvp.model.entity.Template;
import com.demin.alexandr.assistant.mvp.model.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface Repository {

    Single<User> getUser(String uid);

    Completable addNewUser(User user);

    Completable deleteUser(String uid);

    Completable updateUserData(User user);

    Single<List<Template>> getTemplates(String uid);

    Completable addNewTemplate(Template template);

    Completable deleteTemplate(Template template);

    Completable updateTemplateData(Template template);

    Single<List<Pass>> getPasses(String uid);

    Completable addNewPass(Pass pass);

    Completable deletePass(Pass pass);

    Completable updatePassData(Pass pass);

}
