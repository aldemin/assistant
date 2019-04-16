package com.demin.alexandr.assistant.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.activities.MainPresenter;
import com.demin.alexandr.assistant.mvp.view.activities.MainView;
import com.demin.alexandr.assistant.utils.ToolbarManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @Inject
    NavigatorHolder navigatorHolder;
    @Inject
    ToolbarManager toolbarManager;

    @BindView(R.id.ac_main_toolbar)
    Toolbar toolbar;

    private Navigator navigator = new SupportAppNavigator(this, R.id.ac_main_container);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getInstance().getAppComponent().inject(MainActivity.this);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbarManager.init(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            presenter.backPressed();
        } else if (item.getItemId() == R.id.toolbar_menu_settings) {
            presenter.settingsPressed();
        } else if (item.getItemId() == R.id.toolbar_menu_change_workspace) {
            presenter.changePressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        setSupportActionBar(toolbar);
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.singOutFirebaseUser();
    }
}
