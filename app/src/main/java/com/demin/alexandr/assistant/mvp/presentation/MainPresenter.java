package com.demin.alexandr.assistant.mvp.presentation;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.Constants;
import com.demin.alexandr.assistant.mvp.view.MainView;
import com.demin.alexandr.assistant.ui.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Router router;

    @Inject
    Constants constants;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(MainPresenter.this);
        router.replaceScreen(new Screens.LoginFragmentScreen(constants.getEmptyBundle()));
    }

    public void backPressed() {
        router.exit();
    }
}
