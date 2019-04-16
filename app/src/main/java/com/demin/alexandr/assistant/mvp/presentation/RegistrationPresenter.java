package com.demin.alexandr.assistant.mvp.presentation;

import android.annotation.SuppressLint;
import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.auth.Authentication;
import com.demin.alexandr.assistant.mvp.model.repository.Repository;
import com.demin.alexandr.assistant.mvp.view.RegistrationView;
import com.demin.alexandr.assistant.ui.screens.MainScreens;
import com.demin.alexandr.assistant.utils.ToolbarManager;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView> {

    @Inject
    Router router;
    @Inject
    ToolbarManager toolbarManager;
    @Inject
    Authentication authentication;
    @Inject
    @Named("firebase")
    Repository firebaseRepository;
    @Inject
    @Named("mainThread")
    Scheduler mainThread;

    private Context context;

    private final String emptyString = "";

    public RegistrationPresenter(Context context) {
        this.context = context;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(RegistrationPresenter.this);
        toolbarManager.toolbarGone();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.context = null;
    }

    public void registrationPressed(String firstName,
                                    String lastName,
                                    String email,
                                    String password,
                                    String passwordConfirm,
                                    boolean isTeacher) {
        if (email.trim().equals(this.emptyString) || password.trim().equals(this.emptyString)) {
            showErrorMessage("Email or password is empty");
        }
        if (!password.equals(passwordConfirm)) {
            showErrorMessage("Password");
        } else {
            showLoadingDialog();
            addNewUserToFirebase(firstName, lastName, email, password, isTeacher);
        }
    }


    @SuppressLint("CheckResult")
    private void addNewUserToFirebase(String firstName, String lastName, String email, String password, boolean isTeacher) {
        authentication.registration(context, email, password)
                .andThen(authentication.getCurrentUser())
                .flatMapCompletable(user -> {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setTeacher(isTeacher);
                    return firebaseRepository.addNewUser(user);
                })
                .doOnComplete(() -> {
                    hideLoadingDialog();
                    if (isTeacher) {
                        moveToMainTeacherFragment();
                    } else {
                        moveToMainStudentFragment();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread)
                .subscribe(() -> {
                }, throwable -> {
                    hideLoadingDialog();
                    showErrorMessage(throwable.getMessage());
                    authentication.deleteUser()
                            .subscribeOn(Schedulers.io())
                            .subscribe();
                });
    }

    private void showErrorMessage(String message) {
        getViewState().showErrorMessage(message);
    }

    public void cancelPressed() {
        router.backTo(new MainScreens.LoginFragmentScreen());
    }

    private void moveToMainTeacherFragment() {
        router.newRootScreen(new MainScreens.MainTeacherFragmentScreen());
    }

    private void moveToMainStudentFragment() {
        router.newRootScreen(new MainScreens.MainStudentFragmentScreen());
    }

    private void showLoadingDialog() {
        getViewState().showLoadingDialog();
    }

    private void hideLoadingDialog() {
        getViewState().hideLoadingDialog();
    }

}
