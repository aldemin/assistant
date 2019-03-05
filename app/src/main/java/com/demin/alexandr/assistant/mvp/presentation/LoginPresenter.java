package com.demin.alexandr.assistant.mvp.presentation;

import android.app.Activity;
import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.Constants;
import com.demin.alexandr.assistant.mvp.view.LoginView;
import com.demin.alexandr.assistant.ui.Screens;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    @Inject
    Router router;
    @Inject
    Constants constants;
    @Inject
    FirebaseAuth firebaseAuth;

    private Context context;

    public LoginPresenter(Context context) {
        this.context = context;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(LoginPresenter.this);
    }

    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, task -> {
                    if (task.isSuccessful()) {
                        moveToPassFragment();
                    } else {
                        showErrorMessage(task.getException().getMessage());
                    }
                });
    }

    private void showErrorMessage(String message) {
        getViewState().showErrorMessage(message);
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
