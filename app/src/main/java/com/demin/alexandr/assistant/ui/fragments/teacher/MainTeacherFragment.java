package com.demin.alexandr.assistant.ui.fragments.teacher;


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
import com.demin.alexandr.assistant.mvp.presentation.fragments.teacher.MainTeacherFragmentPresenter;
import com.demin.alexandr.assistant.mvp.view.fragments.teacher.MainTeacherFragmentView;
import com.demin.alexandr.assistant.subnavigation.LocalNavigatorHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainTeacherFragment extends MvpAppCompatFragment implements MainTeacherFragmentView {

    public static final String NAME = "MainTeacherFragment";

    private Navigator navigator;

    @BindView(R.id.fr_main_bottom_menu)
    BottomNavigationView bottomNavigationView;

    @Inject
    LocalNavigatorHolder localNavigatorHolder;

    @InjectPresenter
    MainTeacherFragmentPresenter presenter;

    @ProvidePresenter
    public MainTeacherFragmentPresenter mainFragmentPresenter() {
        return new MainTeacherFragmentPresenter(localNavigatorHolder.getCicerone(NAME).getRouter());
    }

    public static MainTeacherFragment newInstance() {
        return new MainTeacherFragment();
    }

    public static MainTeacherFragment newInstance(Bundle args) {
        MainTeacherFragment fragment = new MainTeacherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getAppComponent().inject(MainTeacherFragment.this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_teacher, container, false);
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
