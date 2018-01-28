package com.mojka.organizations.ui.presenter;

import com.mojka.organizations.data.model.Client;
import com.mojka.organizations.data.model.Order;
import com.mojka.organizations.ui.adapter.OrderHistoryListAdapter;
import com.mojka.organizations.ui.contract.HistoryOrderListContract;

import java.util.LinkedList;
import java.util.List;

public class HistoryOrderListPresenterImpl implements HistoryOrderListContract.Presenter {
    private HistoryOrderListContract.View view;

    @Override
    public void start() {
        view.setupUi();
    }

    @Override
    public void setView(HistoryOrderListContract.View view) {
        this.view = view;
    }

    @Override
    public OrderHistoryListAdapter getAdapter() {
        Client client = new Client("Client name (hist)", "Car name");

        List<Order> orders = new LinkedList<>();
        orders.add(new Order(1, 1517141718403L, client));
        orders.add(new Order(2, 1517141718403L, client));
        orders.add(new Order(3, 1517141718403L, client));
        orders.add(new Order(4, 1517141718403L, client));
        orders.add(new Order(5, 1517141718403L, client));
        orders.add(new Order(6, 1517141718403L, client));

        return new OrderHistoryListAdapter(orders, view.getViewContext());
    }
}
