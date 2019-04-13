package com.demin.alexandr.assistant.mvp.view.fragments.teacher;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.demin.alexandr.assistant.mvp.model.entity.Template;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface TemplateView extends MvpView {
    void updateTemplatesList();

    void showLoading();

    void hideLoading();

    void showEmptyList();

    void hideEmptyList();

    void showTemplateList();

    void hideTemplateList();

    void showAddTemplateDialog();

    void hideAddTemplateDialog();

    void showEditTemplateDialog(Template template);

    void hideEditTemplateDialog();
}
