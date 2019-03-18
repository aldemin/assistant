package com.demin.alexandr.assistant.di;

import com.demin.alexandr.assistant.di.modules.AppModule;
import com.demin.alexandr.assistant.di.modules.AppSettingsModule;
import com.demin.alexandr.assistant.di.modules.AuthModule;
import com.demin.alexandr.assistant.di.modules.DbModule;
import com.demin.alexandr.assistant.di.modules.LocalNavigationModule;
import com.demin.alexandr.assistant.di.modules.NavigationModule;
import com.demin.alexandr.assistant.di.modules.ToolbarModule;
import com.demin.alexandr.assistant.mvp.presentation.LoginPresenter;
import com.demin.alexandr.assistant.mvp.presentation.MainFragmentPresenter;
import com.demin.alexandr.assistant.mvp.presentation.MainPresenter;
import com.demin.alexandr.assistant.mvp.presentation.PassPresenter;
import com.demin.alexandr.assistant.mvp.presentation.RegistrationPresenter;
import com.demin.alexandr.assistant.ui.activities.MainActivity;
import com.demin.alexandr.assistant.ui.fragments.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules ={
        AppModule.class,
        LocalNavigationModule.class,
        NavigationModule.class,
        AuthModule.class,
        AppSettingsModule.class,
        DbModule.class,
        ToolbarModule.class
})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);

    void inject(MainPresenter mainPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(RegistrationPresenter registrationPresenter);

    void inject(PassPresenter passPresenter);

    void inject(MainFragmentPresenter mainFragmentPresenter);
}
