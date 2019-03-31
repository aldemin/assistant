package com.demin.alexandr.assistant.mvp.presentation;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.auth.Authentication;
import com.demin.alexandr.assistant.mvp.model.entity.Template;
import com.demin.alexandr.assistant.mvp.model.repository.Repository;
import com.demin.alexandr.assistant.mvp.view.TemplateView;
import com.demin.alexandr.assistant.recycle.viewholder.templates.TemplateListViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class TemplatePresenter extends MvpPresenter<TemplateView> {

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

    private List<Template> templateList = new ArrayList<>();
    private TemplatesListPresenter templatesListPresenter = new TemplatesListPresenter();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(TemplatePresenter.this);
        loadTemplates();
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

    private void showTemplateList() {
        getViewState().showTemplateList();
    }

    private void hideTemplateList() {
        getViewState().hideTemplateList();
    }


    @SuppressLint("CheckResult")
    private void loadTemplates() {
        hideTemplateList();
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

    public TemplatesListPresenter getTemplatesListPresenter() {
        return templatesListPresenter;
    }

    public class TemplatesListPresenter {

        public void bindList(int position, TemplateListViewHolder view) {
            if (templateList != null) {
                Template template = templateList.get(position);
                if (template != null) {
                    view.setTitle(template.getTitle());
                    view.setDaysToFreeze(template.getDaysToFreeze());
                    view.setExpiryDate(template.getExpiryDate());
                    view.setQuantityOfVisits(template.getQuantityOfVisits());
                    view.setQuantityToSkip(template.getQuantityToSkip());
                    view.setStartDate(template.getStartDate());
                    view.setUnlimited(template.isUnlimited());
                }
            }
        }

        public int getCount() {
            return templateList.size();
        }

        public void openRedactorDialog(int position) {
            // TODO: 06.03.2019
        }

        public void itemPressed() {

        }
    }
}
