package com.demin.alexandr.assistant.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface TemplateView extends MvpView {
    void updateTemplatesList();

    void showLoading();

    void hideLoading();

    void showEmptyList();

    void hideEmptyList();

    void showTemplateList();

    void hideTemplateList();
}
