package com.mojka.organizations.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mojka.organizations.R;
import com.mojka.organizations.ui.activity.RegisterActivity;
import com.mojka.organizations.ui.contract.RegisterContract;
import com.mojka.organizations.ui.presenter.RegisterFourthStagePresenterImpl;
import com.rey.material.widget.Button;
import com.rey.material.widget.ProgressView;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFourthStageFragment extends BaseFragment implements RegisterContract.FourthStage.View {
    private final String TAG = "RegisterFourthStage";

    @BindView(R.id.progress_view)
    public ProgressView progressView;

    @BindView(R.id.btn_continue)
    public Button btnContinue;

    @BindView(R.id.et_email)
    public EditText etEmail;

    @BindView(R.id.tv_error)
    public TextView tvError;

    private RegisterContract.FourthStage.Presenter presenter = new RegisterFourthStagePresenterImpl();

    @Override
    @SuppressLint("MissingPermission")
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.setView(this);
        presenter.start();
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public RegisterActivity getViewActivity() {
        return ((RegisterActivity) getActivity());
    }

    @Override
    public void setupUi() {

    }

    @Override
    public void showToast(int text) {
        Toast.makeText(getViewContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getViewContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    @OnClick(R.id.btn_continue)
    public void register() {
        presenter.register(etEmail.getText().toString());
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_register_fourth_stage;
    }

    @Override
    public RegisterContract.FourthStage.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void showProgressBar() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showButton() {
        btnContinue.setClickable(true);
    }

    @Override
    public void hideButton() {
        btnContinue.setClickable(false);
    }

    @Override
    public void setErrorText(String text) {
        this.tvError.setText(text);
    }
}
