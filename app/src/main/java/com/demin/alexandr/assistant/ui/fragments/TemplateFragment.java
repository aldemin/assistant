package com.demin.alexandr.assistant.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.TemplatePresenter;
import com.demin.alexandr.assistant.mvp.view.TemplateView;

public class TemplateFragment extends MvpAppCompatFragment implements TemplateView {

    public static final String NAME = "TemplateFragment";

    @InjectPresenter
    TemplatePresenter presenter;

    public static TemplateFragment newInstance() {
        return new TemplateFragment();
    }

    public static TemplateFragment newInstance(Bundle args) {
        TemplateFragment fragment = new TemplateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_template, container, false);
        return view;
    }

}
