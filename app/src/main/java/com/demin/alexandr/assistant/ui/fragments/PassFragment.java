package com.demin.alexandr.assistant.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.PassPresenter;
import com.demin.alexandr.assistant.mvp.view.PassView;

import butterknife.ButterKnife;

public class PassFragment extends MvpAppCompatFragment implements PassView {

    @InjectPresenter
    PassPresenter presenter;

    public static PassFragment newInstance(Bundle args) {
        PassFragment fragment = new PassFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pass, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
