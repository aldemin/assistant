package com.demin.alexandr.assistant.ui.fragments;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.PassPresenter;
import com.demin.alexandr.assistant.mvp.view.PassView;
import com.demin.alexandr.assistant.recycle.adapter.PassListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PassFragment extends MvpAppCompatFragment implements PassView {

    public static final String NAME = "PassFragment";

    @InjectPresenter
    PassPresenter presenter;

    @BindView(R.id.fr_pass_list)
    RecyclerView passList;

    private PassListAdapter adapter;

    public static PassFragment newInstance() {
        return new PassFragment();
    }

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
        initAdapter();
        return view;
    }

    private void initAdapter() {
        adapter = new PassListAdapter(presenter.getPassListPresenter());
        passList.setAdapter(adapter);
        passList.setLayoutManager(new LinearLayoutManager(getContext()));
        passList.setItemAnimator(new DefaultItemAnimator());
    }

    @OnClick(R.id.fr_pass_fab)
    @Override
    public void cameraFabPressed() {
        // TODO: 06.03.2019
    }

    @Override
    public void updatePassList() {
        adapter.notifyDataSetChanged();
    }
}
