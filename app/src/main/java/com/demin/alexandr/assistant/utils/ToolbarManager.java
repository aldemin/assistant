package com.demin.alexandr.assistant.utils;

import android.support.v7.widget.Toolbar;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ToolbarManager {

    private Toolbar toolbar;

    public void init(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public void toolbarVisible() {
        toolbar.setVisibility(VISIBLE);
    }

    public void toolbarGone() {
        toolbar.setVisibility(GONE);
    }
}
