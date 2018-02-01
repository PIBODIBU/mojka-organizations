package com.mojka.organizations.ui.activity.order;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mojka.organizations.R;
import com.mojka.organizations.ui.activity.BaseActivity;
import com.mojka.organizations.ui.activity.BaseNavDrawerActivity;
import com.mojka.organizations.ui.contract.HistoryOrderListContract;
import com.mojka.organizations.ui.presenter.HistoryOrderListPresenterImpl;
import com.rey.material.widget.ProgressView;

import butterknife.BindView;

public class HistoryOrderListActivity extends BaseNavDrawerActivity implements HistoryOrderListContract.View {
    public static final String TAG = "HistoryOrderList";

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.progress_view)
    public ProgressView progressView;

    private HistoryOrderListContract.Presenter presenter = new HistoryOrderListPresenterImpl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.setView(this);
        presenter.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_order_list;
    }

    @Override
    public String getActivityTitle() {
        return getString(R.string.activity_history_order_list);
    }

    @Override
    protected OnCloseButtonListener getOnCloseButtonListener() {
        return null;
    }

    @Override
    protected Boolean showCloseButton() {
        return false;
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public Activity getViewActivity() {
        return this;
    }

    @Override
    public void setupUi() {
        setupRecyclerView();
    }

    @Override
    public void setupRecyclerView() {
        recyclerView.setAdapter(presenter.getAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoading(Boolean loading) {
        if (loading)
            progressView.setVisibility(View.VISIBLE);
        else
            progressView.setVisibility(View.GONE);
    }
}
