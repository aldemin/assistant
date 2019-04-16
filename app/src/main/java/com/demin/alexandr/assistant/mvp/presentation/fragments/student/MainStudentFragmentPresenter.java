package com.demin.alexandr.assistant.mvp.presentation.fragments.student;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.auth.Authentication;
import com.demin.alexandr.assistant.mvp.model.repository.Repository;
import com.demin.alexandr.assistant.mvp.view.fragments.student.MainStudentFragmentView;
import com.demin.alexandr.assistant.utils.ToolbarManager;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainStudentFragmentPresenter extends MvpPresenter<MainStudentFragmentView> {

    @Inject
    ToolbarManager toolbarManager;
    @Inject
    Router router;
    @Inject
    @Named("firebase")
    Repository firebaseRepository;
    @Inject
    Authentication firebaseAuthentication;
    @Inject
    @Named("mainThread")
    Scheduler mainThread;

/*    private List<Pass> passes = new ArrayList<>();
    private MainStudentFragmentPresenter.PassListPresenter passListPresenter =
            new MainStudentFragmentPresenter.PassListPresenter();*/

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(MainStudentFragmentPresenter.this);
        toolbarManager.toolbarVisible();
        //loadPasses();
    }

/*    @SuppressLint("CheckResult")
    private void loadPasses() {
        hidePassList();
        hideEmptyList();
        showLoading();
        firebaseAuthentication.getCurrentUser()
                .flatMap(user -> firebaseRepository.getTemplates(user.getUid()))
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread)
                .subscribe(templates -> {
                    hideLoading();
                    if (templates.size() == 0) {
                        showEmptyList();
                    } else {
                        showTemplateList();
                        templateList = templates;
                        getViewState().updateTemplatesList();
                    }
                }, throwable -> { });
    }

    private void showLoading() {
        getViewState().showLoading();
    }

    private void hideLoading() {
        getViewState().hideLoading();
    }

    private void showEmptyList() {
        getViewState().showEmptyList();
    }

    private void hideEmptyList() {
        getViewState().hideEmptyList();
    }

    private void showPassList() {
        getViewState().showTemplateList();
    }

    private void hidePassList() {
        getViewState().hideTemplateList();
    }

    public class PassListPresenter {
    }*/
}
