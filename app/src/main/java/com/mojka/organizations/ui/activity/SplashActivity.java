package com.mojka.organizations.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mojka.organizations.R;
import com.mojka.organizations.ui.contract.SplashContract;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class SplashActivity extends BaseActivity implements SplashContract.View {
    @BindView(R.id.iv_logo)
    public ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                startNextActivity();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
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
    public void startNextActivity() {
        SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void setupUi() {
        Picasso.with(this)
                .load(R.drawable.ic_logo)
                .into(ivLogo);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public String getActivityTitle() {
        return null;
    }

    @Override
    protected OnCloseButtonListener getOnCloseButtonListener() {
        return null;
    }
}