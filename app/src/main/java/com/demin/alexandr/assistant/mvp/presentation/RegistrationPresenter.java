package com.demin.alexandr.assistant.mvp.presentation;

import android.app.Activity;
import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.view.RegistrationView;
import com.demin.alexandr.assistant.ui.Screens.MainScreens;
import com.demin.alexandr.assistant.utils.ToolbarManager;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView> {

    @Inject
    Router router;
    @Inject
    FirebaseAuth firebaseAuth;
    @Inject
    ToolbarManager toolbarManager;

    private Context context;

    private final String emptyString = "";

    public RegistrationPresenter(Context context) {
        this.context = context;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(RegistrationPresenter.this);
        toolbarManager.toolbarGone();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.context = null;
    }

    public void registrationPressed(String email, String password, String passwordConfirm) {
        if (email.trim().equals(this.emptyString) || password.trim().equals(this.emptyString)) {
            showErrorMessage("Email or password is empty");
        }
        if (!password.equals(passwordConfirm)) {
            showErrorMessage("Password");
        } else {
            // TODO: 04.03.2019
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, task -> {
                        if (task.isSuccessful()) {
                            moveToMainFragment();
                        } else {
                            showErrorMessage(task.getException().getMessage());
                        }
                    });
        }
    }

    private void showErrorMessage(String message) {
        getViewState().showErrorMessage(message);
    }

    public void cancelPressed() {
        router.backTo(new MainScreens.LoginFragmentScreen());
    }

    private void moveToMainFragment() {
        router.newRootScreen(new MainScreens.MainFragmentScreen());
    }

}
