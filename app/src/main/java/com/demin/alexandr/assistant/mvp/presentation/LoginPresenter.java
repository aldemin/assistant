package com.demin.alexandr.assistant.mvp.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.Constants;
import com.demin.alexandr.assistant.mvp.view.LoginView;
import com.demin.alexandr.assistant.ui.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    @Inject
    Router router;
    @Inject
    Constants constants;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(LoginPresenter.this);
    }

    public void login(String login, String password) {
        // TODO: 27.02.2019 add logic
        moveToPassFragment();
    }

    public void moveToRegistrationFragment() {
        router.navigateTo(new Screens.RegistrationFragmentScreen(constants.getEmptyBundle()));
    }

    public void moveToForgotPassFragment() {

    }

    public void moveToPassFragment() {
        router.newRootScreen(new Screens.PassFragmentScreen(constants.getEmptyBundle()));
    }
}
