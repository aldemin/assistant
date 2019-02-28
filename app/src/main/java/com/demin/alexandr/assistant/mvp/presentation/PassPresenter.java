package com.demin.alexandr.assistant.mvp.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.Constants;
import com.demin.alexandr.assistant.mvp.view.PassView;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class PassPresenter extends MvpPresenter<PassView> {

    @Inject
    Router router;
    @Inject
    Constants constants;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(PassPresenter.this);
    }
}
