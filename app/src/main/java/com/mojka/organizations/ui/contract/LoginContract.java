package com.mojka.organizations.ui.contract;

import android.support.annotation.StringRes;

import com.mojka.organizations.ui.contract.base.BasePresenter;
import com.mojka.organizations.ui.contract.base.BaseView;

public interface LoginContract {
    interface View extends BaseView {
        void loadBackground();

        void startRegisterActivity();

        String getPhoneNumber();

        String getPassword();

        void showToast(@StringRes int text);

        void showToast(String text);

        void startNextActivity();

        void login();

        void showProgressBar();

        void hideProgressBar();

        void freezeUI();

        void unfreezeUI();

        void forgotPassword();
    }

    interface Presenter extends BasePresenter<View> {
        void login();

        void checkIntent();
    }
}
