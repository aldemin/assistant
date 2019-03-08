package com.demin.alexandr.assistant.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.demin.alexandr.assistant.ui.fragments.LoginFragment;
import com.demin.alexandr.assistant.ui.fragments.PassFragment;
import com.demin.alexandr.assistant.ui.fragments.RegistrationFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

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

    public static class PassFragmentScreen extends SupportAppScreen {

        private Bundle bundle;

        public PassFragmentScreen() {
            this.bundle = bundle;
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

}
