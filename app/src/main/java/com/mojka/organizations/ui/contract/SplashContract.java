package com.mojka.organizations.ui.contract;

import com.mojka.organizations.ui.contract.base.BasePresenter;
import com.mojka.organizations.ui.contract.base.BaseView;

public interface SplashContract {
    interface View extends BaseView {
        void startNextActivity();
    }

    interface Presenter extends BasePresenter<View> {

    }
}