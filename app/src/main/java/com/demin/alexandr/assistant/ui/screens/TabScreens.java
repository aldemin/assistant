package com.demin.alexandr.assistant.ui.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.demin.alexandr.assistant.ui.fragments.teacher.PassFragment;
import com.demin.alexandr.assistant.ui.fragments.teacher.TemplateFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class TabScreens {

    public static class PassFragmentScreen extends SupportAppScreen {

        private Bundle bundle;

        public PassFragmentScreen() {
        }

        public PassFragmentScreen(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        public Fragment getFragment() {
            if (this.bundle != null) {
                return PassFragment.newInstance(bundle);
            }
            return PassFragment.newInstance();
        }
    }

    public static class TemplateFragmentScreen extends SupportAppScreen {

        private Bundle bundle;

        public TemplateFragmentScreen() {
        }

        public TemplateFragmentScreen(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        public Fragment getFragment() {
            if (this.bundle != null) {
                return TemplateFragment.newInstance(bundle);
            }
            return TemplateFragment.newInstance();
        }
    }
}
