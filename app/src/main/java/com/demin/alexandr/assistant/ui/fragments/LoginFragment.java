package com.demin.alexandr.assistant.ui.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.LoginPresenter;
import com.demin.alexandr.assistant.mvp.view.LoginView;
import com.demin.alexandr.assistant.ui.dialogs.LoadingDialog;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends MvpAppCompatFragment implements LoginView {

    @InjectPresenter
    LoginPresenter presenter;

    @BindView(R.id.fr_login_field_login)
    EditText fieldLogin;
    @BindView(R.id.fr_login_field_password)
    EditText fieldPassword;
    @BindView(R.id.fr_login_check_remember_me)
    CheckBox rememberMe;

    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    @Named("Remember me")
    String rememberMeTag;

    private View view;
    private LoadingDialog loadingDialog;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public static LoginFragment newInstance(Bundle args) {
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    public LoginPresenter loginPresenter() {
        return new LoginPresenter(getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_login, container, false);
        initLoadingDialog();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.view = null;
    }

    @OnClick(R.id.fr_login_btn_login)
    @Override
    public void loginPressed() {
        presenter.login(this.fieldLogin.getText().toString(),
                this.fieldPassword.getText().toString(),
                this.rememberMe.isChecked());
    }

    @OnClick(R.id.fr_login_text_forgot_pass)
    @Override
    public void forgotPassPressed() {
        presenter.moveToForgotPassFragment();
    }

    @OnClick(R.id.fr_login_text_register)
    @Override
    public void registrationPressed() {
        presenter.moveToRegistrationFragment();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(this.view, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog.show(getFragmentManager(), "loading");
    }

    @Override
    public void hideLoadingDialog() {
        loadingDialog.dismiss();
    }

    void initLoadingDialog() {
        loadingDialog = new LoadingDialog();
        loadingDialog.setCancelable(false);
    }

}
