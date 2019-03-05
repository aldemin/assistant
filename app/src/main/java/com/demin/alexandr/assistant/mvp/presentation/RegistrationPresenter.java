package com.demin.alexandr.assistant.mvp.presentation;

import android.app.Activity;
import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.Constants;
import com.demin.alexandr.assistant.mvp.view.RegistrationView;
import com.demin.alexandr.assistant.ui.Screens;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView> {

    @Inject
    Router router;
    @Inject
    Constants constants;
    @Inject
    FirebaseAuth firebaseAuth;

    private Context context;

    public RegistrationPresenter(Context context) {
        this.context = context;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(RegistrationPresenter.this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.context = null;
    }

    public void registrationPressed(String name, String email, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            showErrorMessage("Password");
        } else {
            // TODO: 04.03.2019
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, task -> {
                        if (task.isSuccessful()) {
                            moveToPassFragment();
                        } else {
                            showErrorMessage(task.getException().getMessage());
                        }
                    });
        }
    }

    public void showErrorMessage(String message) {
        getViewState().showErrorMessage(message);
    }

    public void cancelPressed() {
        router.backTo(new Screens.LoginFragmentScreen(constants.getEmptyBundle()));
    }

    public void moveToPassFragment() {
        router.newRootScreen(new Screens.PassFragmentScreen(constants.getEmptyBundle()));
    }
}
