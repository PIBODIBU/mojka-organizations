package com.mojka.organizations.ui.contract;

import com.mojka.organizations.ui.adapter.OrderHistoryListAdapter;
import com.mojka.organizations.ui.contract.base.BasePresenter;
import com.mojka.organizations.ui.contract.base.BaseView;

public interface HistoryOrderListContract {
    interface View extends BaseView {
        void setupRecyclerView();
    }

    interface Presenter extends BasePresenter<View> {
        OrderHistoryListAdapter getAdapter();
    }
}
