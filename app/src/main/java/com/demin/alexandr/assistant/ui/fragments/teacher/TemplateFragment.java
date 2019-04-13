package com.demin.alexandr.assistant.ui.fragments.teacher;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.demin.alexandr.assistant.mvp.model.entity.Template;
import com.demin.alexandr.assistant.mvp.presentation.fragments.teacher.TemplatePresenter;
import com.demin.alexandr.assistant.mvp.view.fragments.teacher.TemplateView;
import com.demin.alexandr.assistant.recycle.adapter.TemplateListAdapter;
import com.demin.alexandr.assistant.ui.dialogs.AddAndEditTemplateDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TemplateFragment extends MvpAppCompatFragment implements TemplateView, SwipeRefreshLayout.OnRefreshListener {

    @InjectPresenter
    TemplatePresenter presenter;

    @BindView(R.id.fr_templates_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fr_templates_list)
    RecyclerView templateList;
    @BindView(R.id.fr_templates_empty_text)
    TextView emptyTextView;
    @BindView(R.id.fr_templates_loading)
    ProgressBar loadingBar;
    @BindView(R.id.fr_templates_fab)
    FloatingActionButton addTemplate;

    private TemplateListAdapter adapter;
    private AddAndEditTemplateDialog addAndEditTemplateDialog;

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
        initListeners();
        return view;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> {
            swipeRefreshLayout.setRefreshing(false);
            presenter.loadTemplates();
        }, 100);
    }

    private void initAdapter() {
        adapter = new TemplateListAdapter(presenter.getTemplatesListPresenter());
        templateList.setAdapter(adapter);
        templateList.setLayoutManager(new LinearLayoutManager(getContext()));
        templateList.setItemAnimator(new DefaultItemAnimator());
    }

    private void initListeners() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showAddTemplateDialog() {
        addAndEditTemplateDialog = AddAndEditTemplateDialog.newInstance(getString(R.string.headline_add_new_template));
        addAndEditTemplateDialog.setTemplatePresenter(presenter);
        addAndEditTemplateDialog.show(getChildFragmentManager(), "add template");
    }

    @Override
    public void showEditTemplateDialog(Template template) {
        addAndEditTemplateDialog = AddAndEditTemplateDialog.newInstance(getString(R.string.headline_edit_template), template);
        addAndEditTemplateDialog.setTemplatePresenter(presenter);
        addAndEditTemplateDialog.show(getChildFragmentManager(), "edit template");
    }

    @Override
    public void hideEditTemplateDialog() {
        addAndEditTemplateDialog.dismiss();
    }

    @Override
    public void hideAddTemplateDialog() {
        addAndEditTemplateDialog.dismiss();
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

    @OnClick(R.id.fr_templates_fab)
    public void fabPressed() {
        presenter.fabPressed();
    }

}
