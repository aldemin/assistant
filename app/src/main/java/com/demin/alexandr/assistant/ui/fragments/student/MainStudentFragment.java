package com.demin.alexandr.assistant.ui.fragments.student;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.fragments.student.MainStudentFragmentPresenter;
import com.demin.alexandr.assistant.mvp.view.fragments.student.MainStudentFragmentView;

public class MainStudentFragment extends MvpAppCompatFragment implements MainStudentFragmentView {

    @InjectPresenter
    MainStudentFragmentPresenter presenter;

    public static MainStudentFragment newInstance() {
        return new MainStudentFragment();
    }

    public static MainStudentFragment newInstance(Bundle args) {
        MainStudentFragment fragment = new MainStudentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_student, container, false);
        return view;
    }


}
