package com.mojka.organizations.ui.presenter;

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
import com.mojka.organizations.ui.activity.RegisterActivity;
import com.mojka.organizations.ui.contract.RegisterContract;

import java.util.LinkedList;
import java.util.List;

public class RegisterFourthStagePresenterImpl implements RegisterContract.FourthStage.Presenter {
    private final String TAG = "RegisterFourthStage";
    private RegisterContract.FourthStage.View view;
    private LinkedList<AuthCallback> authCallbacks = new LinkedList<>();

    @Override
    public void start() {
        view.setupUi();

        addAuthCallback(new AuthCallback() {
            @Override
            public void onStart(String email) {
                view.setErrorText("");
                view.hideButton();
                view.showProgressBar();
            }

            @Override
            public void onSuccess() {
                view.showButton();
                view.hideProgressBar();
            }

            @Override
            public void onError() {
                view.setErrorText(view.getViewActivity().getString(R.string.error_auth_phone));
                view.showButton();
                view.hideProgressBar();
            }
        });
    }

    @Override
    public void addAuthCallback(AuthCallback authCallback) {
        if (authCallback != null)
            authCallbacks.add(authCallback);
    }

    @Override
    public List<AuthCallback> getAuthCallbacks() {
        return authCallbacks;
    }

    @Override
    public void register(String email) {
        if (TextUtils.isEmpty(email)) {
            view.setErrorText(view.getViewActivity().getString(R.string.error));
            return;
        }

        for (AuthCallback authCallback : getAuthCallbacks())
            authCallback.onStart(email);

        APIGenerator.createService(LoginAPI.class).register(
                ((RegisterActivity) view.getViewActivity()).getUser().getPhone(),
                ((RegisterActivity) view.getViewActivity()).getUser().getPassword(),
                ((RegisterActivity) view.getViewActivity()).getUser().getEmail(),
                ((RegisterActivity) view.getViewActivity()).getUser().getName()
        ).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onError() {
                view.showToast(R.string.error_register);

                for (AuthCallback authCallback : getAuthCallbacks())
                    authCallback.onError();
            }

            @Override
            public void onSuccess(LoginResponse response) {
                if (response.getError()) {
                    for (AuthCallback authCallback : getAuthCallbacks())
                        authCallback.onError();
                    view.showToast(response.getMessage());
                    return;
                }

                final String token = response.getToken();

                APIGenerator.createService(ProfileAPI.class).get(token).enqueue(new Callback<BaseDataWrapper<User>>() {
                    @Override
                    public void onSuccess(BaseDataWrapper<User> response) {
                        User user = response.getResponseObj();
                        user.setToken(token);

                        new AccountService(view.getViewContext()).setAccount(response.getResponseObj());

                        for (AuthCallback authCallback : getAuthCallbacks())
                            authCallback.onSuccess();
                    }

                    @Override
                    public void onError() {
                        view.showToast(R.string.error_register);

                        for (AuthCallback authCallback : getAuthCallbacks())
                            authCallback.onError();
                    }
                });
            }
        });
    }

    @Override
    public void setView(RegisterContract.FourthStage.View view) {
        this.view = view;
    }

    public static abstract class AuthCallback {
        public void onStart(String email) {
        }

        public void onSuccess() {
        }

        public void onError() {
        }
    }
}
