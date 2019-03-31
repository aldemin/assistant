package com.demin.alexandr.assistant.mvp.model.repository;

import com.demin.alexandr.assistant.mvp.model.entity.Template;
import com.demin.alexandr.assistant.mvp.model.entity.User;
import com.demin.alexandr.assistant.mvp.model.parser.FirebaseDocumentsParser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class FirebaseRepository implements Repository {

    private static final String COLLECTION_USERS_TAG = "users";
    private static final String COLLECTION_PASS_TAG = "pass";
    private static final String COLLECTION_TEMPLATES_TAG = "my_templates";

    private FirebaseFirestore firestore;
    private FirebaseDocumentsParser parser;

    public FirebaseRepository(FirebaseFirestore firestore, FirebaseDocumentsParser parser) {
        this.firestore = firestore;
        this.parser = parser;
    }

    @Override
    public Single<User> getUser(String uid) {
        return Single.create(emitter -> firestore.collection(COLLECTION_USERS_TAG)
                .document(uid)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().getData() == null) {
                            emitter.onError(new RuntimeException());
                        }
                        emitter.onSuccess(parser.toUser(task.getResult().getData()));
                    } else {
                        emitter.onError(task.getException());
                    }
                })
        );
    }

    @Override
    public Completable addNewUser(User user) {
        return Completable.create(emitter -> firestore.collection(COLLECTION_USERS_TAG)
                .document(user.getUid())
                .set(parser.userToMap(user))
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(task.getException());
                    }
                }));
    }



    @Override
    public Completable deleteUser(String uid) {
        return Completable.create(emitter -> firestore.collection(COLLECTION_USERS_TAG)
                .document(uid)
                .delete()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(task.getException());
                    }
                }));
    }

    @Override
    public Completable updateUserData(User user) {
        return Completable.create(emitter -> firestore.collection(COLLECTION_USERS_TAG)
                .document(user.getUid())
                .update(parser.userToMap(user))
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(task.getException());
                    }
                }));
    }

    @Override
    public Single<List<Template>> getTemplates(User user) {
        return Single.create(emitter -> firestore.collection(COLLECTION_USERS_TAG)
                .document(user.getUid())
                .collection(COLLECTION_TEMPLATES_TAG)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Template> templateList = new ArrayList<>();
                        if (task.getResult().getDocuments() == null) {
                            emitter.onSuccess(templateList);
                        }
                        for (DocumentSnapshot document : task.getResult().getDocuments()) {
                            templateList.add(parser.toTemplate(user.getUid(),document.getId(), document.getData()));
                        }
                        emitter.onSuccess(templateList);
                    } else {
                        emitter.onError(task.getException());
                    }
                })
        );
    }

    @Override
    public Single<List<Template>> getTemplates(String uid) {
        return Single.create(emitter -> firestore.collection(COLLECTION_USERS_TAG)
                .document(uid)
                .collection(COLLECTION_TEMPLATES_TAG)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Template> templateList = new ArrayList<>();
                        if (task.getResult().getDocuments() == null) {
                            emitter.onSuccess(templateList);
                        }
                        for (DocumentSnapshot document : task.getResult().getDocuments()) {
                            templateList.add(parser.toTemplate(uid,document.getId(), document.getData()));
                        }
                        emitter.onSuccess(templateList);
                    } else {
                        emitter.onError(task.getException());
                    }
                })
        );
    }

    @Override
    public Completable addNewTemplate(Template template) {
        return Completable.create(emitter -> firestore.collection(COLLECTION_TEMPLATES_TAG)
                .document(template.getOwnerId())
                .collection(COLLECTION_TEMPLATES_TAG)
                .add(parser.templateToMap(template))
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(task.getException());
                    }
                }));
    }

    @Override
    public Completable deleteTemplate(Template template) {
        return Completable.create(emitter -> firestore.collection(COLLECTION_TEMPLATES_TAG)
                .document(template.getOwnerId())
                .collection(COLLECTION_TEMPLATES_TAG)
                .document(template.getId())
                .delete()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(task.getException());
                    }
                }));
    }

    @Override
    public Completable updateTemplateData(Template template) {
        return Completable.create(emitter -> firestore.collection(COLLECTION_TEMPLATES_TAG)
                .document(template.getOwnerId())
                .collection(COLLECTION_TEMPLATES_TAG)
                .document(template.getId())
                .update(parser.templateToMap(template))
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(task.getException());
                    }
                }));
    }
}
