package com.demin.alexandr.assistant.di;

import com.demin.alexandr.assistant.di.modules.AndroidSchedulerModule;
import com.demin.alexandr.assistant.di.modules.AppModule;
import com.demin.alexandr.assistant.di.modules.AppSettingsModule;
import com.demin.alexandr.assistant.di.modules.AuthModule;
import com.demin.alexandr.assistant.di.modules.FirebaseModule;
import com.demin.alexandr.assistant.di.modules.LocalNavigationModule;
import com.demin.alexandr.assistant.di.modules.NavigationModule;
import com.demin.alexandr.assistant.di.modules.ParserModule;
import com.demin.alexandr.assistant.di.modules.RepositoriesModule;
import com.demin.alexandr.assistant.di.modules.ToolbarModule;
import com.demin.alexandr.assistant.mvp.presentation.LoginPresenter;
import com.demin.alexandr.assistant.mvp.presentation.RegistrationPresenter;
import com.demin.alexandr.assistant.mvp.presentation.activities.MainPresenter;
import com.demin.alexandr.assistant.mvp.presentation.fragments.student.MainStudentFragmentPresenter;
import com.demin.alexandr.assistant.mvp.presentation.fragments.teacher.MainTeacherFragmentPresenter;
import com.demin.alexandr.assistant.mvp.presentation.fragments.teacher.PassPresenter;
import com.demin.alexandr.assistant.mvp.presentation.fragments.teacher.TemplatePresenter;
import com.demin.alexandr.assistant.ui.activities.MainActivity;
import com.demin.alexandr.assistant.ui.dialogs.AddAndEditTemplateDialog;
import com.demin.alexandr.assistant.ui.fragments.teacher.MainTeacherFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules ={
        AppModule.class,
        LocalNavigationModule.class,
        NavigationModule.class,
        AuthModule.class,
        AppSettingsModule.class,
        ToolbarModule.class,
        AndroidSchedulerModule.class,
        RepositoriesModule.class,
        ParserModule.class,
        FirebaseModule.class
})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(MainTeacherFragment mainTeacherFragment);

    void inject(AddAndEditTemplateDialog addAndEditTemplateDialog);

    void inject(MainPresenter mainPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(RegistrationPresenter registrationPresenter);

    void inject(PassPresenter passPresenter);

    void inject(TemplatePresenter templatePresenter);

    void inject(TemplatePresenter.TemplatesListPresenter templatesListPresenter);

    void inject(MainTeacherFragmentPresenter mainTeacherFragmentPresenter);

    void inject(MainStudentFragmentPresenter mainTeacherFragmentPresenter);
}
