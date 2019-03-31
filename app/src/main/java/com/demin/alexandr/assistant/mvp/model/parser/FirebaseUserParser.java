package com.demin.alexandr.assistant.mvp.model.parser;

import com.demin.alexandr.assistant.mvp.model.entity.User;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseUserParser {

    public User parse(FirebaseUser firebaseUser) {
        // TODO: 21.03.2019 add db
        User user = new User();
        user.setUid(firebaseUser.getUid());
        return user;
    }

}
