package com.mojka.organizations.ui.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.mojka.organizations.R;
import com.mojka.organizations.data.account.AccountService;
import com.mojka.organizations.data.api.APIGenerator;
import com.mojka.organizations.data.api.inrerfaces.LoginAPI;
import com.mojka.organizations.data.api.inrerfaces.ProfileAPI;
import com.mojka.organizations.data.callback.Callback;
import com.mojka.organizations.data.model.BaseDataWrapper;
import com.mojka.organizations.data.model.LoginResponse;
import com.mojka.organizations.data.model.User;
import com.mojka.organizations.ui.activity.LoginActivity;
import com.mojka.organizations.ui.contract.LoginContract;

public class LoginPresenterImpl implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenterImpl";

    private LoginContract.View view;
    private Boolean finishAfterSuccess = false;

    @Override
    public void start() {
        view.setupUi();
        checkIntent();
    }

    @Override
    public void checkIntent() {
        Intent intent = view.getViewActivity().getIntent();

        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey(LoginActivity.KEY_FINISH_AFTER_SUCCESS))
            finishAfterSuccess = intent.getExtras().getBoolean(LoginActivity.KEY_FINISH_AFTER_SUCCESS, false);
    }

    @Override
    public void login() {
        if (TextUtils.isEmpty(view.getPhoneNumber()) || TextUtils.isEmpty(view.getPassword())) {
            view.showToast(R.string.enter_phone_and_password);
            return;
        }

        view.showProgressBar();
        view.freezeUI();

        APIGenerator.createService(LoginAPI.class).login(view.getPhoneNumber(), view.getPassword()).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                if (response.getToken() == null) {
                    onDone();
                    onError();
                    return;
                }

                final String token = response.getToken();

                APIGenerator.createService(ProfileAPI.class).get(token).enqueue(new Callback<BaseDataWrapper<User>>() {
                    @Override
                    public void onError() {
                        view.showToast(R.string.error);
                    }

                    @Override
                    public void onSuccess(BaseDataWrapper<User> response) {
                        User user = response.getResponseObj();

                        if (user == null) {
                            onError();
                            return;
                        }

                        user.setToken(token);

                        new AccountService(view.getViewContext()).setAccount(response.getResponseObj());

                        if (finishAfterSuccess)
                            view.getViewActivity().finish();
                        else
                            view.startNextActivity();
                    }
                });
            }

            @Override
            public void onError() {
                view.showToast(R.string.error_login);
            }

            @Override
            public void onDone() {
                view.unfreezeUI();
                view.hideProgressBar();
            }
        });
    }

    @Override
    public void setView(LoginContract.View view) {
        this.view = view;
    }
}
