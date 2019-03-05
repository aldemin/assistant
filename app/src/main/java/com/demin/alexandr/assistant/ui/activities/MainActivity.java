package com.demin.alexandr.assistant.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.MainPresenter;
import com.demin.alexandr.assistant.mvp.view.MainView;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @Inject
    NavigatorHolder navigatorHolder;

    private Navigator navigator = new SupportAppNavigator(this, R.id.ac_main_container);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getInstance().getAppComponent().inject(MainActivity.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            presenter.backPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

}
