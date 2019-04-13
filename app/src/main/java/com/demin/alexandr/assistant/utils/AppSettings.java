package com.demin.alexandr.assistant.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSettings {

    private static final String PREFERENCES_NAME_TAG = "App Settings";
    private static final String PREFERENCES_REMEMBER_ME_TAG = "Remember me";
    private static final String PREFERENCES_IS_TEACHER_TAG = "Is teacher";
    private static final String PREFERENCES_IS_TEACHER_WORKSPACE_MODE_TAG = "Is teacher workspace mode";

    private SharedPreferences sharedPreferences;

    public AppSettings(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME_TAG, Context.MODE_PRIVATE);
    }

    public boolean isRememberMe() {
        return sharedPreferences.getBoolean(PREFERENCES_REMEMBER_ME_TAG, false);
    }

    public void setRememberMe(boolean rememberMe) {
        sharedPreferences.edit().putBoolean(PREFERENCES_REMEMBER_ME_TAG, rememberMe).apply();
    }

    public boolean isTeacher() {
        return sharedPreferences.getBoolean(PREFERENCES_IS_TEACHER_TAG, false);
    }

    public void setTeacher(boolean teacher) {
        sharedPreferences.edit().putBoolean(PREFERENCES_IS_TEACHER_TAG, teacher).apply();
    }

    public boolean isTeacherWorkspaceMode() {
        return sharedPreferences.getBoolean(PREFERENCES_IS_TEACHER_WORKSPACE_MODE_TAG, false);
    }

    public void setTeacherWorkspaceMode(boolean teacherWorkspaceMode) {
        sharedPreferences.edit().putBoolean(PREFERENCES_IS_TEACHER_WORKSPACE_MODE_TAG, teacherWorkspaceMode).apply();
    }
}
