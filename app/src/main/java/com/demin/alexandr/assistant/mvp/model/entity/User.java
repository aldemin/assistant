package com.demin.alexandr.assistant.mvp.model.entity;

import java.util.List;
import java.util.Objects;

public class User {

    public static final String USER_FIRST_NAME_FIELD_KEY = "first_name";
    public static final String USER_LAST_NAME_FIELD_KEY = "last_name";
    public static final String USER_IS_TEACHER_FIELD_KEY = "is_teacher";
    public static final String USER_TEMPLATE_LIST_ID_FIELD_KEY = "templateList";
    public static final String USER_PASS_LIST_ID_FIELD_KEY = "passList";

    private String uid;
    private String firstName;
    private String lastName;
    private boolean isTeacher;

    private List<Template> templateList;
    private List<Pass> passList;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public List<Template> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<Template> templateList) {
        this.templateList = templateList;
    }

    public List<Pass> getPassList() {
        return passList;
    }

    public void setPassList(List<Pass> passList) {
        this.passList = passList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isTeacher=" + isTeacher +
                ", templateList=" + templateList +
                ", passList=" + passList +
                '}';
    }
}
