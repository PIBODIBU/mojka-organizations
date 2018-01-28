package com.mojka.organizations.ui.presenter;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mojka.organizations.R;
import com.mojka.organizations.ui.contract.RegisterContract;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class RegisterFirstStagePresenterImpl implements RegisterContract.FirstStage.Presenter {
    private final String TAG = "RegisterFirstPresenter";

    private RegisterContract.FirstStage.View view;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private LinkedList<AuthCallback> authCallbacks = new LinkedList<>();

    @Override
    public void start() {
        setOnVerificationStateChangedCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                for (AuthCallback authCallback : authCallbacks)
                    authCallback.onError();

                view.hideProgressBar();
                view.showButton();
                view.setErrorText(view.getViewActivity().getString(R.string.error_auth_phone));
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                for (AuthCallback authCallback : authCallbacks)
                    authCallback.onSuccess(verificationId);

                view.hideProgressBar();
                view.showButton();
            }
        });
    }

    @Override
    public void setView(RegisterContract.FirstStage.View view) {
        this.view = view;
    }

    @Override
    public void verifyPhoneNumber(String name, String phoneNumber) {
        for (AuthCallback authCallback : authCallbacks)
            authCallback.onStart(name, phoneNumber);

        FirebaseApp.initializeApp(view.getViewActivity());

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,    // Phone number to verify
                getAuthTimeDuration(), // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                view.getViewActivity(), // Activity (for callback binding)
                getOnVerificationStateChangedCallbacks());
    }

    @Override
    public int getAuthTimeDuration() {
        return 60;
    }

    @Override
    public void setOnVerificationStateChangedCallbacks(PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public PhoneAuthProvider.OnVerificationStateChangedCallbacks getOnVerificationStateChangedCallbacks() {
        return callbacks;
    }

    @Override
    public void addAuthCallback(AuthCallback authCallback) {
        if (authCallback != null)
            this.authCallbacks.add(authCallback);
    }

    @Override
    public LinkedList<AuthCallback> getAuthCallbacks() {
        return authCallbacks;
    }

    public static abstract class AuthCallback {
        public void onStart(String name, String phoneNumber) {
        }

        public void onSuccess(String verificationId) {
        }

        public void onError() {
        }
    }
}
