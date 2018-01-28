package com.mojka.organizations.ui.presenter;

import com.mojka.organizations.data.model.Client;
import com.mojka.organizations.data.model.Order;
import com.mojka.organizations.ui.adapter.OrderActiveListAdapter;
import com.mojka.organizations.ui.contract.ActiveOrderListContract;

import java.util.LinkedList;
import java.util.List;

public class ActiveOrderListPresenterImpl implements ActiveOrderListContract.Presenter {
    private ActiveOrderListContract.View view;

    @Override
    public void start() {
        view.setupUi();
    }

    @Override
    public void setView(ActiveOrderListContract.View view) {
        this.view = view;
    }

    @Override
    public OrderActiveListAdapter getAdapter() {
        Client client = new Client("Client name", "Car name");

        List<Order> orders = new LinkedList<>();
        orders.add(new Order(1, 1517141718403L, client));
        orders.add(new Order(2, 1517141718403L, client));
        orders.add(new Order(3, 1517141718403L, client));
        orders.add(new Order(4, 1517141718403L, client));
        orders.add(new Order(5, 1517141718403L, client));
        orders.add(new Order(6, 1517141718403L, client));

        return new OrderActiveListAdapter(orders, view.getViewContext());
    }
}
