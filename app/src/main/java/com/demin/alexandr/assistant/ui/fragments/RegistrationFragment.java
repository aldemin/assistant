package com.demin.alexandr.assistant.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.RegistrationPresenter;
import com.demin.alexandr.assistant.mvp.view.RegistrationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationFragment extends MvpAppCompatFragment implements RegistrationView {

    @InjectPresenter
    RegistrationPresenter presenter;

    @BindView(R.id.fr_registration_field_first_name)
    EditText fieldFirstName;
    @BindView(R.id.fr_registration_field_last_name)
    EditText fieldLastName;
    @BindView(R.id.fr_registration_field_email)
    EditText fieldEmail;
    @BindView(R.id.fr_registration_field_password)
    EditText fieldPassword;
    @BindView(R.id.fr_registration_field_password_confirm)
    EditText fieldPasswordConfirm;
    @BindView(R.id.fr_registration_checkbox_teacher)
    CheckBox isTeacherCheckBox;

    private View view;
    private AlertDialog loadingDialog;

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    public static RegistrationFragment newInstance(Bundle args) {
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    public RegistrationPresenter registrationPresenter() {
        return new RegistrationPresenter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_registration, container, false);
        initLoadingDialog();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.view = null;
    }

    @OnClick(R.id.fr_registration_btn_register)
    @Override
    public void registrationPressed() {
        presenter.registrationPressed(fieldFirstName.getText().toString(),
                fieldLastName.getText().toString(),
                fieldEmail.getText().toString(),
                fieldPassword.getText().toString(),
                fieldPasswordConfirm.getText().toString(),
                isTeacherCheckBox.isChecked());
    }

    @OnClick(R.id.fr_registration_btn_cancel)
    @Override
    public void cancelPressed() {
        presenter.cancelPressed();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(this.view, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        loadingDialog.hide();
    }

    private void initLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.dialog_loading);
        builder.setCancelable(false);
        loadingDialog = builder.create();
    }
}
