package com.mojka.organizations.ui.contract;

import com.mojka.organizations.ui.adapter.OrderActiveListAdapter;
import com.mojka.organizations.ui.contract.base.BasePresenter;
import com.mojka.organizations.ui.contract.base.BaseView;

public interface ActiveOrderListContract {
    interface View extends BaseView {
        void setupRecyclerView();
    }

    interface Presenter extends BasePresenter<View> {
        OrderActiveListAdapter getAdapter();
    }
}
