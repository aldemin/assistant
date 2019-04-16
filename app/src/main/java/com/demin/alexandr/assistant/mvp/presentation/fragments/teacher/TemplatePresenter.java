package com.demin.alexandr.assistant.mvp.presentation.fragments.teacher;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.auth.Authentication;
import com.demin.alexandr.assistant.mvp.model.entity.Template;
import com.demin.alexandr.assistant.mvp.model.parser.DateParser;
import com.demin.alexandr.assistant.mvp.model.repository.Repository;
import com.demin.alexandr.assistant.mvp.view.fragments.teacher.TemplateView;
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

    public void showAddTemplateDialog() {
        getViewState().showAddTemplateDialog();
    }

    public void showEditTemplateDialog(Template template) {
        getViewState().showEditTemplateDialog(template);
    }

    public void hideAddTemplateDialog() {
        getViewState().hideAddTemplateDialog();
    }

    @SuppressLint("CheckResult")
    public void loadTemplates() {
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
                }, throwable -> {
                    // TODO: 02.04.2019
                });
    }

    public TemplatesListPresenter getTemplatesListPresenter() {
        return templatesListPresenter;
    }

    public void fabPressed() {
        showAddTemplateDialog();
    }

    public class TemplatesListPresenter {

        @Inject
        DateParser dateParser;

        public TemplatesListPresenter() {
            App.getInstance().getAppComponent().inject(TemplatesListPresenter.this);
        }

        public void bindList(int position, TemplateListViewHolder view) {
            if (templateList != null) {
                Template template = templateList.get(position);
                if (template != null) {
                    view.setOwnerId(template.getOwnerId());
                    view.setId(template.getId());
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

        public DateParser getDateParser() {
            return dateParser;
        }

        public int getCount() {
            return templateList.size();
        }

        public void openRedactorDialog(int position) {
            // TODO: 06.03.2019
        }

        public void itemPressed(Template template) {
            showEditTemplateDialog(template);
        }
    }
}
