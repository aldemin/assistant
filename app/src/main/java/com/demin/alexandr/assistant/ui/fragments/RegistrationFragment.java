package com.demin.alexandr.assistant.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.RegistrationPresenter;
import com.demin.alexandr.assistant.mvp.view.RegistrationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationFragment extends MvpAppCompatFragment implements RegistrationView {

    @InjectPresenter
    RegistrationPresenter presenter;

    @BindView(R.id.fr_registration_field_name)
    EditText fieldName;
    @BindView(R.id.fr_registration_field_email)
    EditText fieldEmail;
    @BindView(R.id.fr_registration_field_login)
    EditText fieldLogin;
    @BindView(R.id.fr_registration_field_password)
    EditText fieldPassword;
    @BindView(R.id.fr_registration_field_password_confirm)
    EditText fieldPasswordConfirm;

    public static RegistrationFragment newInstance(Bundle args) {
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fr_registration_btn_register)
    @Override
    public void registrationPressed() {
        presenter.registrationPressed(fieldName.getText().toString(),
                fieldLogin.getText().toString(),
                fieldEmail.getText().toString(),
                fieldPassword.getText().toString(),
                fieldPasswordConfirm.getText().toString());
    }

    @OnClick(R.id.fr_registration_btn_cancel)
    @Override
    public void cancelPressed() {
        presenter.cancelPressed();
    }
}
