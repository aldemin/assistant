package com.demin.alexandr.assistant.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface RegistrationView extends MvpView {
    void registrationPressed();
    void cancelPressed();
    void showErrorMessage(String message);
}
