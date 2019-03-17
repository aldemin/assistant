package com.demin.alexandr.assistant.ui.dialogs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.demin.alexandr.assistant.R;

public class TemplateEditDialog extends MvpAppCompatDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_template_edit_dialog, container, false);
        return view;
    }

}
