package com.mojka.organizations.ui.presenter;

import com.mojka.organizations.R;
import com.mojka.organizations.data.account.AccountService;
import com.mojka.organizations.data.api.APIGenerator;
import com.mojka.organizations.data.api.inrerfaces.OrderAPI;
import com.mojka.organizations.data.callback.Callback;
import com.mojka.organizations.data.model.BaseDataWrapper;
import com.mojka.organizations.data.model.Order;
import com.mojka.organizations.ui.adapter.OrderActiveListAdapter;
import com.mojka.organizations.ui.contract.ActiveOrderListContract;

import java.util.ArrayList;
import java.util.List;

public class ActiveOrderListPresenterImpl implements ActiveOrderListContract.Presenter {
    private static final String TAG = "ActiveOrderPresenter";

    private ActiveOrderListContract.View view;
    private OrderActiveListAdapter adapter;

    @Override
    public void start() {
        fetchData();
        createAdapter();
        view.setupUi();
    }

    @Override
    public void setView(ActiveOrderListContract.View view) {
        this.view = view;
    }

    @Override
    public void fetchData() {
        view.setLoading(true);

        APIGenerator.createService(OrderAPI.class).getActive(new AccountService(view.getViewContext()).getToken()).enqueue(new Callback<BaseDataWrapper<List<Order>>>() {
            @Override
            public void onSuccess(BaseDataWrapper<List<Order>> response) {
                if (response.getError()) {
                    onError();
                    onDone();
                    return;
                }

                adapter.setOrders(response.getResponseObj());
            }

            @Override
            public void onError() {
                view.showToast(R.string.error);
            }

            @Override
            public void onDone() {
                view.setLoading(false);
            }
        });
    }

    @Override
    public OrderActiveListAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void createAdapter() {
        adapter = new OrderActiveListAdapter(new ArrayList<>(), view.getViewContext());
    }
}
