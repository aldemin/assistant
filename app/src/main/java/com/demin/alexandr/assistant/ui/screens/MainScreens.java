package com.demin.alexandr.assistant.ui.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.demin.alexandr.assistant.ui.fragments.LoginFragment;
import com.demin.alexandr.assistant.ui.fragments.RegistrationFragment;
import com.demin.alexandr.assistant.ui.fragments.student.MainStudentFragment;
import com.demin.alexandr.assistant.ui.fragments.teacher.MainTeacherFragment;

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

    public static class MainTeacherFragmentScreen extends SupportAppScreen {

        private Bundle bundle;

        public MainTeacherFragmentScreen() {
        }

        public MainTeacherFragmentScreen(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        public Fragment getFragment() {
            if (this.bundle != null) {
                return MainTeacherFragment.newInstance(this.bundle);
            }
            return MainTeacherFragment.newInstance();
        }
    }

    public static class MainStudentFragmentScreen extends SupportAppScreen {

        private Bundle bundle;

        public MainStudentFragmentScreen() {
        }

        public MainStudentFragmentScreen(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        public Fragment getFragment() {
            if (this.bundle != null) {
                return MainStudentFragment.newInstance(this.bundle);
            }
            return MainStudentFragment.newInstance();
        }
    }
}
