package com.demin.alexandr.assistant.mvp.presentation;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.view.MainView;
import com.demin.alexandr.assistant.ui.screens.MainScreens;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;
import javax.inject.Named;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

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
    @Named("isFirstRun")
    String isFirstRunTag;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(MainPresenter.this);
        checkFirstRun();
        initFirebaseUser();
    }

    private void checkFirstRun() {
        if (sharedPreferences.getBoolean(this.isFirstRunTag, true)) {
            sharedPreferences.edit().putBoolean(this.isFirstRunTag, false).apply();
            sharedPreferences.edit().putBoolean(this.rememberMeTag, false).apply();
        }
    }

    private void initFirebaseUser() {
        if (!sharedPreferences.getBoolean(this.rememberMeTag, false)
                && firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.signOut();
        }
        if (firebaseAuth.getCurrentUser() == null) {
            moveToLoginScreen();
        } else {
            moveToMainScreen();
        }
    }

    public void singOutFirebaseUser() {
        if (!sharedPreferences.getBoolean(this.rememberMeTag, false)) {
            firebaseAuth.signOut();
        }
    }

    private void moveToMainScreen() {
        router.replaceScreen(new MainScreens.MainFragmentScreen());
    }

    private void moveToLoginScreen() {
        router.replaceScreen(new MainScreens.LoginFragmentScreen());
    }

    public void backPressed() {
        router.exit();
    }
}
