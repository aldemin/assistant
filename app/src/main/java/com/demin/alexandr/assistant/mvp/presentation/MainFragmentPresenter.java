package com.demin.alexandr.assistant.mvp.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.view.MainFragmentView;
import com.demin.alexandr.assistant.ui.Screens.TabScreens;
import com.demin.alexandr.assistant.utils.ToolbarManager;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainFragmentPresenter extends MvpPresenter<MainFragmentView> {

    @Inject
    ToolbarManager toolbarManager;

    private Router router;

    public MainFragmentPresenter(Router router) {
        this.router = router;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(MainFragmentPresenter.this);
        toolbarManager.toolbarVisible();
        moveToTemplatesFragment();
    }

    public void templatesPressed() {
        moveToTemplatesFragment();
    }

    public void passesPressed() {
        moveToPassFragment();
    }

    private void moveToPassFragment() {
        router.replaceScreen(new TabScreens.PassFragmentScreen());
    }

    private void moveToTemplatesFragment() {
        router.replaceScreen(new TabScreens.TemplateFragmentScreen());
    }

    public void onBackPressed() {
        router.exit();
    }
}
