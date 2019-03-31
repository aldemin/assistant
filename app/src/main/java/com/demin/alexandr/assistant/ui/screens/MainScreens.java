package com.demin.alexandr.assistant.ui.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.demin.alexandr.assistant.ui.fragments.LoginFragment;
import com.demin.alexandr.assistant.ui.fragments.MainFragment;
import com.demin.alexandr.assistant.ui.fragments.RegistrationFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class MainScreens {

    public static class LoginFragmentScreen extends SupportAppScreen {

        private Bundle bundle;

        public LoginFragmentScreen() {

        }

        public LoginFragmentScreen(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        public Fragment getFragment() {
            if (this.bundle != null) {
                return LoginFragment.newInstance(bundle);
            }
            return LoginFragment.newInstance();
        }
    }

    public static class RegistrationFragmentScreen extends SupportAppScreen {

        private Bundle bundle;

        public RegistrationFragmentScreen() {

        }

        public RegistrationFragmentScreen(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        public Fragment getFragment() {
            if (this.bundle != null) {
                return RegistrationFragment.newInstance(bundle);
            }
            return RegistrationFragment.newInstance();
        }
    }

    public static class MainFragmentScreen extends SupportAppScreen {

        private Bundle bundle;

        public MainFragmentScreen() {
        }

        public MainFragmentScreen(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        public Fragment getFragment() {
            if (this.bundle != null) {
                return MainFragment.newInstance(this.bundle);
            }
            return MainFragment.newInstance();
        }
    }
}
