package com.demin.alexandr.assistant.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.MainFragmentPresenter;
import com.demin.alexandr.assistant.mvp.view.MainFragmentView;
import com.demin.alexandr.assistant.subnavigation.LocalNavigatorHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainFragment extends MvpAppCompatFragment implements MainFragmentView {

    public static final String NAME = "MainFragment";

    private Navigator navigator;

    @BindView(R.id.fr_main_bottom_menu)
    BottomNavigationView bottomNavigationView;

    @Inject
    LocalNavigatorHolder localNavigatorHolder;

    @InjectPresenter
    MainFragmentPresenter presenter;

    @ProvidePresenter
    public MainFragmentPresenter mainFragmentPresenter() {
        return new MainFragmentPresenter(localNavigatorHolder.getCicerone(NAME).getRouter());
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public static MainFragment newInstance(Bundle args) {
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getAppComponent().inject(MainFragment.this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        initBottomBar();
        return view;
    }

    private void initBottomBar() {
        bottomNavigationView.setSelectedItemId(R.id.main_fr_bottom_menu_templates);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.main_fr_bottom_menu_templates) {
                templatesPressed();
            } else if (menuItem.getItemId() == R.id.main_fr_bottom_menu_passes) {
                passesPressed();
            }
            return true;
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        getCicerone().getNavigatorHolder().setNavigator(getNavigator());
    }

    @Override
    public void onPause() {
        getCicerone().getNavigatorHolder().removeNavigator();
        super.onPause();
    }

    @Override
    public void templatesPressed() {
        presenter.templatesPressed();
    }

    @Override
    public void passesPressed() {
        presenter.passesPressed();
    }

    private Cicerone<Router> getCicerone() {
        return localNavigatorHolder.getCicerone(NAME);
    }

    private Navigator getNavigator() {
        if (navigator == null) {
            navigator = new SupportAppNavigator(getActivity(), getChildFragmentManager(), R.id.fr_main_container);
        }
        return navigator;
    }

}
