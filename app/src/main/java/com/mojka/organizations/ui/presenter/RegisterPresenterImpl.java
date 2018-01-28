package com.mojka.organizations.ui.presenter;

import com.google.firebase.FirebaseApp;
import com.mojka.organizations.ui.contract.RegisterContract;

public class RegisterPresenterImpl implements RegisterContract.Presenter {
    private final String TAG = "RegisterPresenterImpl";

    private RegisterContract.View view;

    @Override
    public void start() {
    }

    @Override
    public void setView(RegisterContract.View view) {
        this.view = view;
    }
}
