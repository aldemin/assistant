package com.demin.alexandr.assistant.mvp.presentation.activities;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.view.activities.MainView;
import com.demin.alexandr.assistant.ui.screens.MainScreens;
import com.demin.alexandr.assistant.utils.AppSettings;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Router router;
    @Inject
    FirebaseAuth firebaseAuth;
    @Inject
    AppSettings appSettings;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(MainPresenter.this);
        initRememberMeFlag();
    }

    private void initRememberMeFlag() {
        if (appSettings.isRememberMe()) {
            initWorkspace();
        } else {
            moveToLoginScreen();
        }
    }

    private void initWorkspace() {
        if (firebaseAuth.getCurrentUser() != null) {
            if (appSettings.isTeacher()) {
                if (appSettings.isTeacherWorkspaceMode()) {
                    moveToMainTeacherScreen();
                } else {
                    moveToMainStudentScreen();
                }
            } else {
                moveToMainStudentScreen();
            }
        } else {
            moveToLoginScreen();
        }
    }

    public void singOutFirebaseUser() {
        if (appSettings.isRememberMe()) {
            firebaseAuth.signOut();
        }
    }

    private void moveToMainTeacherScreen() {
        router.newRootScreen(new MainScreens.MainTeacherFragmentScreen());
    }

    private void moveToMainStudentScreen() {
        router.newRootScreen(new MainScreens.MainStudentFragmentScreen());
    }

    private void moveToLoginScreen() {
        router.replaceScreen(new MainScreens.LoginFragmentScreen());
    }

    public void backPressed() {
        router.exit();
    }

    public void settingsPressed() {

    }

    public void changePressed() {
        if (appSettings.isTeacherWorkspaceMode()) {
            moveToMainStudentScreen();
            appSettings.setTeacherWorkspaceMode(false);
        } else {
            appSettings.setTeacherWorkspaceMode(true);
            moveToMainTeacherScreen();
        }
    }
}
