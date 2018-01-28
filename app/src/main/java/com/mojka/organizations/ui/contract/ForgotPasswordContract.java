package com.mojka.organizations.ui.contract;

import com.mojka.organizations.ui.contract.base.BasePresenter;
import com.mojka.organizations.ui.contract.base.BaseView;

public interface ForgotPasswordContract {
    interface View extends BaseView {
        void showSaveButton();

        void hideSaveButton();

        void showGetCodeButton();

        void hideGetCodeButton();

        void showEtPassword();

        void hideEtPassword();

        void setEtPhoneEditable(Boolean editable);

        void sendCode();

        void save();

        String getPassword();

        String getPhone();

        String getCode();
    }

    interface Presenter extends BasePresenter<View> {
        void save();

        void sendCode();

        void verifyCode();
    }
}