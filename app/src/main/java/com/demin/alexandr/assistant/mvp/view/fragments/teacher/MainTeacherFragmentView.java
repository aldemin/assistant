package com.demin.alexandr.assistant.mvp.view.fragments.teacher;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainTeacherFragmentView extends MvpView {
    void templatesPressed();
    void passesPressed();
}
