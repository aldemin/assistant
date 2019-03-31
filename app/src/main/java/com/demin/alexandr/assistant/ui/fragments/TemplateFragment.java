package com.demin.alexandr.assistant.ui.fragments;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.TemplatePresenter;
import com.demin.alexandr.assistant.mvp.view.TemplateView;
import com.demin.alexandr.assistant.recycle.adapter.TemplateListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemplateFragment extends MvpAppCompatFragment implements TemplateView {

    public static final String NAME = "TemplateFragment";

    @InjectPresenter
    TemplatePresenter presenter;

    @BindView(R.id.fr_templates_list)
    RecyclerView templateList;
    @BindView(R.id.fr_templates_empty_text)
    TextView emptyTextView;
    @BindView(R.id.fr_templates_loading)
    ProgressBar loadingBar;

    private TemplateListAdapter adapter;

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
        ButterKnife.bind(this, view);
        initAdapter();
        return view;
    }

    private void initAdapter() {
        adapter = new TemplateListAdapter(presenter.getTemplatesListPresenter());
        templateList.setAdapter(adapter);
        templateList.setLayoutManager(new LinearLayoutManager(getContext()));
        templateList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void updateTemplatesList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyList() {
        emptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyList() {
        emptyTextView.setVisibility(View.GONE);
    }

    @Override
    public void showTemplateList() {
        templateList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTemplateList() {
        templateList.setVisibility(View.GONE);
    }

}
