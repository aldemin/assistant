package com.demin.alexandr.assistant.mvp.presentation;

import android.annotation.SuppressLint;
import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.auth.Authentication;
import com.demin.alexandr.assistant.mvp.model.repository.Repository;
import com.demin.alexandr.assistant.mvp.view.LoginView;
import com.demin.alexandr.assistant.ui.screens.MainScreens;
import com.demin.alexandr.assistant.utils.AppSettings;
import com.demin.alexandr.assistant.utils.ToolbarManager;

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
    ToolbarManager toolbarManager;
    @Inject
    Authentication authentication;
    @Inject
    AppSettings appSettings;
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
                    .flatMap(uid -> firebaseRepository.getUser(uid))
                    .subscribeOn(Schedulers.io())
                    .observeOn(mainThread)
                    .subscribe(user -> {
                        hideLoadingDialog();
                        if (isRememberMe) {
                            appSettings.setRememberMe(true);
                        }
                        appSettings.setTeacher(user.isTeacher());
                        if (user.isTeacher()) {
                            appSettings.setTeacherWorkspaceMode(true);
                            moveToMainTeacherScreen();
                        } else {
                            appSettings.setTeacherWorkspaceMode(false);
                            moveToMainStudentScreen();
                        }
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

    private void moveToMainTeacherScreen() {
        router.newRootScreen(new MainScreens.MainTeacherFragmentScreen());
    }

    private void moveToMainStudentScreen() {
        router.newRootScreen(new MainScreens.MainStudentFragmentScreen());
    }

    private void showLoadingDialog() {
        getViewState().showLoadingDialog();
    }

    private void hideLoadingDialog() {
        getViewState().hideLoadingDialog();
    }
}
