package com.demin.alexandr.assistant.mvp.model.parser;

import com.demin.alexandr.assistant.mvp.model.entity.Pass;
import com.demin.alexandr.assistant.mvp.model.entity.Template;
import com.demin.alexandr.assistant.mvp.model.entity.User;
import com.google.firebase.Timestamp;

import java.util.HashMap;
import java.util.Map;

import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_DAYS_TO_FREEZE_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_EXPIRY_DATE_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_QUANTITY_OF_VISITS_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_QUANTITY_TO_SKIP_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_START_DATE_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_TITLE_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_UNLIMITED_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.User.USER_FIRST_NAME_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.User.USER_IS_TEACHER_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.User.USER_LAST_NAME_FIELD_KEY;

public class FirebaseDocumentsParser {

    public User toUser(Map<String, Object> document) {
        User user = new User();
        user.setFirstName((String) document.get(USER_FIRST_NAME_FIELD_KEY));
        user.setLastName((String) document.get(USER_LAST_NAME_FIELD_KEY));
        if (document.get(USER_IS_TEACHER_FIELD_KEY) != null) {
            user.setTeacher((Boolean) document.get(USER_IS_TEACHER_FIELD_KEY));
        } else {
            user.setTeacher(false);
        }
        return user;
    }

    public Template toTemplate(String ownerId, String id, Map<String, Object> document) {
        Template template = new Template();
        template.setOwnerId(ownerId);
        template.setId(id);
        template.setTitle((String) document.get(TEMPLATE_TITLE_FIELD_KEY));
        template.setDaysToFreeze((Long) document.get(TEMPLATE_DAYS_TO_FREEZE_FIELD_KEY));
        template.setExpiryDate((Timestamp) document.get(TEMPLATE_EXPIRY_DATE_FIELD_KEY));
        template.setStartDate((Timestamp) document.get(TEMPLATE_START_DATE_FIELD_KEY));
        template.setQuantityOfVisits((Long) document.get(TEMPLATE_QUANTITY_OF_VISITS_FIELD_KEY));
        template.setQuantityToSkip((Long) document.get(TEMPLATE_QUANTITY_TO_SKIP_FIELD_KEY));
        template.setUnlimited((Boolean) document.get(TEMPLATE_UNLIMITED_FIELD_KEY));
        return template;
    }

    public Pass toPass(Map<String, Object> document) {
        Pass pass = new Pass();
        return pass;
    }

    public Map<String, Object> userToMap(User user) {
        Map<String, Object> document = new HashMap<>();
        document.put(USER_FIRST_NAME_FIELD_KEY, user.getFirstName());
        document.put(USER_LAST_NAME_FIELD_KEY, user.getLastName());
        document.put(USER_IS_TEACHER_FIELD_KEY, user.isTeacher());
        return document;
    }

    public Map<String, Object> templateToMap(Template template) {
        Map<String, Object> document = new HashMap<>();
        document.put(TEMPLATE_TITLE_FIELD_KEY, template.getTitle());
        document.put(TEMPLATE_UNLIMITED_FIELD_KEY, template.isUnlimited());
        document.put(TEMPLATE_EXPIRY_DATE_FIELD_KEY, template.getExpiryDate());
        document.put(TEMPLATE_START_DATE_FIELD_KEY, template.getStartDate());
        document.put(TEMPLATE_DAYS_TO_FREEZE_FIELD_KEY, template.getDaysToFreeze());
        document.put(TEMPLATE_QUANTITY_OF_VISITS_FIELD_KEY, template.getQuantityOfVisits());
        document.put(TEMPLATE_QUANTITY_TO_SKIP_FIELD_KEY, template.getQuantityToSkip());
        return document;
    }

    public Map<String, Object> passToMap(Pass pass) {
        Map<String, Object> document = new HashMap<>();
        return document;
    }

}
