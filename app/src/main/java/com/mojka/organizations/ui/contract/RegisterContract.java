package com.mojka.organizations.ui.contract;

import android.support.annotation.StringRes;

import com.google.firebase.auth.PhoneAuthProvider;
import com.mojka.organizations.data.model.User;
import com.mojka.organizations.ui.contract.base.BasePresenter;
import com.mojka.organizations.ui.contract.base.BaseView;
import com.mojka.organizations.ui.presenter.RegisterFirstStagePresenterImpl;
import com.mojka.organizations.ui.presenter.RegisterSecondStagePresenterImpl;
import com.mojka.organizations.ui.presenter.RegisterFourthStagePresenterImpl;
import com.mojka.organizations.ui.presenter.RegisterThirdStagePresenterImpl;

import java.util.List;

public interface RegisterContract {
    interface View extends BaseView {
        void showFirstStage();

        void showSecondStage();

        void showThirdStage();

        void showFourthStage();

        User getUser();
    }

    interface Presenter extends BasePresenter<View> {
    }

    interface FirstStage {
        interface View extends BaseView {
            void next();

            void showProgressBar();

            void hideProgressBar();

            void showButton();

            void hideButton();

            Presenter getPresenter();

            void setErrorText(String text);
        }

        interface Presenter extends BasePresenter<RegisterContract.FirstStage.View> {
            void verifyPhoneNumber(String name, String phoneNumber);

            void setOnVerificationStateChangedCallbacks(PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks);

            PhoneAuthProvider.OnVerificationStateChangedCallbacks getOnVerificationStateChangedCallbacks();

            int getAuthTimeDuration();

            void addAuthCallback(RegisterFirstStagePresenterImpl.AuthCallback authCallback);

            List<RegisterFirstStagePresenterImpl.AuthCallback> getAuthCallbacks();
        }
    }

    interface SecondStage {
        interface View extends BaseView {
            void finishRegistration();

            Presenter getPresenter();

            void showProgressBar();

            void hideProgressBar();

            void showButton();

            void hideButton();

            void setErrorText(String text);

            void showToast(@StringRes int text);
        }

        interface Presenter extends BasePresenter<RegisterContract.SecondStage.View> {
            void signInWithCode(String code);

            void setVerificationId(String verificationId);

            String getVerificationId();

            void addAuthCallback(RegisterSecondStagePresenterImpl.AuthCallback authCallback);

            List<RegisterSecondStagePresenterImpl.AuthCallback> getAuthCallbacks();
        }
    }

    interface ThirdStage {
        interface View extends BaseView {
            Presenter getPresenter();

            void register();

            void showProgressBar();

            void hideProgressBar();

            void showButton();

            void hideButton();

            void setErrorText(String text);
        }

        interface Presenter extends BasePresenter<ThirdStage.View> {
            void register(String password, String passwordRepeat);

            void addAuthCallback(RegisterThirdStagePresenterImpl.AuthCallback authCallback);

            List<RegisterThirdStagePresenterImpl.AuthCallback> getAuthCallbacks();
        }
    }

    interface FourthStage {
        interface View extends BaseView {
            Presenter getPresenter();

            void register();

            void showProgressBar();

            void hideProgressBar();

            void showButton();

            void hideButton();

            void setErrorText(String text);

            void showToast(@StringRes int text);

            void showToast(String text);
        }

        interface Presenter extends BasePresenter<FourthStage.View> {
            void register(String email);

            void addAuthCallback(RegisterFourthStagePresenterImpl.AuthCallback authCallback);

            List<RegisterFourthStagePresenterImpl.AuthCallback> getAuthCallbacks();
        }
    }
}
