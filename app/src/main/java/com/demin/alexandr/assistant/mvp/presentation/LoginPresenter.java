package com.demin.alexandr.assistant.mvp.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.auth.Authentication;
import com.demin.alexandr.assistant.mvp.model.repository.Repository;
import com.demin.alexandr.assistant.mvp.view.LoginView;
import com.demin.alexandr.assistant.ui.screens.MainScreens;
import com.demin.alexandr.assistant.utils.ToolbarManager;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    @Inject
    Router router;
    @Inject
    FirebaseAuth firebaseAuth;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    @Named("Remember me")
    String rememberMeTag;
    @Inject
    ToolbarManager toolbarManager;
    @Inject
    Authentication authentication;
    @Inject
    @Named("firebase")
    Repository firebaseRepository;
    @Inject
    @Named("mainThread")
    Scheduler mainThread;

    private Context context;

    public LoginPresenter(Context context) {
        this.context = context;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(LoginPresenter.this);
        toolbarManager.toolbarGone();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.context = null;
    }

    @SuppressLint("CheckResult")
    public void login(String email, String password, boolean isRememberMe) {
        String emptyString = "";
        if (email.trim().equals(emptyString) || password.trim().equals(emptyString)) {
            showErrorMessage("Email or password is empty");
        } else {
            showLoadingDialog();
            authentication.singIn(context, email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(mainThread)
                    .subscribe(() -> {
                        hideLoadingDialog();
                        moveToMainFragment();
                    }, throwable -> {
                        hideLoadingDialog();
                        showErrorMessage(throwable.getMessage());
                    });
        }
    }


    private void showErrorMessage(String message) {
        getViewState().showErrorMessage(message);
    }

    public void moveToRegistrationFragment() {
        router.navigateTo(new MainScreens.RegistrationFragmentScreen());
    }

    public void moveToForgotPassFragment() {

    }

    private void moveToMainFragment() {
        router.newRootScreen(new MainScreens.MainFragmentScreen());
    }

    private void showLoadingDialog() {
        getViewState().showLoadingDialog();
    }

    private void hideLoadingDialog() {
        getViewState().hideLoadingDialog();
    }
}
