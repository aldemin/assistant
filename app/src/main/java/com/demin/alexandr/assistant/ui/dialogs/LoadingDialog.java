package com.demin.alexandr.assistant.ui.dialogs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.dialogs.LoadingDialogPresenter;
import com.demin.alexandr.assistant.mvp.view.dialogs.LoadingDialogView;

public class LoadingDialog extends MvpAppCompatDialogFragment implements LoadingDialogView {

    @InjectPresenter
    LoadingDialogPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading_dialog, container, false);
    }

}
