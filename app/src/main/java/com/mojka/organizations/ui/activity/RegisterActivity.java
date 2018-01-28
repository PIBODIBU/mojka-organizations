package com.mojka.organizations.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.mojka.organizations.R;
import com.mojka.organizations.data.model.User;
import com.mojka.organizations.ui.activity.order.ActiveOrderListActivity;
import com.mojka.organizations.ui.contract.RegisterContract;
import com.mojka.organizations.ui.fragment.RegisterFirstStageFragment;
import com.mojka.organizations.ui.fragment.RegisterFourthStageFragment;
import com.mojka.organizations.ui.fragment.RegisterSecondStageFragment;
import com.mojka.organizations.ui.fragment.RegisterThirdStageFragment;
import com.mojka.organizations.ui.presenter.RegisterFirstStagePresenterImpl;
import com.mojka.organizations.ui.presenter.RegisterFourthStagePresenterImpl;
import com.mojka.organizations.ui.presenter.RegisterPresenterImpl;
import com.mojka.organizations.ui.presenter.RegisterSecondStagePresenterImpl;
import com.mojka.organizations.ui.presenter.RegisterThirdStagePresenterImpl;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    private final String TAG = "RegisterActivity";

    private RegisterContract.Presenter presenter = new RegisterPresenterImpl();
    private RegisterFirstStageFragment firstStageFragment = new RegisterFirstStageFragment();
    private RegisterSecondStageFragment secondStageFragment = new RegisterSecondStageFragment();
    private RegisterThirdStageFragment thirdStageFragment = new RegisterThirdStageFragment();
    private RegisterFourthStageFragment fourthStageFragment = new RegisterFourthStageFragment();

    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firstStageFragment.getPresenter().addAuthCallback(new RegisterFirstStagePresenterImpl.AuthCallback() {
            @Override
            public void onStart(String name, String phoneNumber) {
                user.setName(name);
                user.setPhone(phoneNumber);
            }

            @Override
            public void onSuccess(String verificationId) {
                secondStageFragment.getPresenter().setVerificationId(verificationId);
                showSecondStage();
            }
        });

        secondStageFragment.getPresenter().addAuthCallback(new RegisterSecondStagePresenterImpl.AuthCallback() {
            @Override
            public void onSuccess() {
                showThirdStage();
            }
        });

        thirdStageFragment.getPresenter().addAuthCallback(new RegisterThirdStagePresenterImpl.AuthCallback() {
            @Override
            public void onSuccess(String password) {
                user.setPassword(password);
                showFourthStage();
            }
        });

        fourthStageFragment.getPresenter().addAuthCallback(new RegisterFourthStagePresenterImpl.AuthCallback() {
            @Override
            public void onStart(String email) {
                user.setEmail(email);
            }

            @Override
            public void onSuccess() {
                startActivity(new Intent(RegisterActivity.this, ActiveOrderListActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        presenter.setView(this);
        presenter.start();
        showFirstStage();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public String getActivityTitle() {
        return getString(R.string.activity_register);
    }

    @Override
    protected OnCloseButtonListener getOnCloseButtonListener() {
        return new OnCloseButtonListener() {
            @Override
            public void onclick() {
                finish();
            }
        };
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public AppCompatActivity getViewActivity() {
        return this;
    }

    @Override
    public void setupUi() {
    }

    @Override
    public void showFirstStage() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, firstStageFragment)
                .commit();
    }

    @Override
    public void showSecondStage() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, 0, 0)
                .replace(R.id.frame, secondStageFragment)
                .commit();
    }

    @Override
    public void showThirdStage() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, 0, 0)
                .replace(R.id.frame, thirdStageFragment)
                .commit();
    }

    @Override
    public void showFourthStage() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, 0, 0)
                .replace(R.id.frame, fourthStageFragment)
                .commit();
    }
}
