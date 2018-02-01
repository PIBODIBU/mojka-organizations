package com.mojka.organizations.ui.contract;

import android.support.annotation.StringRes;

import com.mojka.organizations.ui.adapter.OrderActiveListAdapter;
import com.mojka.organizations.ui.contract.base.BasePresenter;
import com.mojka.organizations.ui.contract.base.BaseView;

public interface ActiveOrderListContract {
    interface View extends BaseView {
        void setupRecyclerView();

        void showToast(@StringRes int message);

        void setLoading(Boolean loading);
    }

    interface Presenter extends BasePresenter<View> {
        OrderActiveListAdapter getAdapter();

        void createAdapter();

        void fetchData();
    }
}
