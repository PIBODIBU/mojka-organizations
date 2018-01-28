package com.mojka.organizations.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mojka.organizations.R;
import com.mojka.organizations.data.model.Order;
import com.mojka.organizations.ui.holder.OrderHistoryViewHolder;
import com.mojka.organizations.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderHistoryListAdapter extends RecyclerView.Adapter<OrderHistoryViewHolder> {
    private static final String TAG = "OrderActiveListAdapter";

    private List<Order> orders;
    private Context context;

    public OrderHistoryListAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @Override
    public OrderHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false));
    }

    @Override
    public void onBindViewHolder(OrderHistoryViewHolder holder, int position) {
        Order order = orders.get(position);

        if (order == null) {
            Log.e(TAG, "onBindViewHolder: Order is null");
            return;
        }

        Picasso.with(context)
                .load(R.drawable.img_info_window)
                .into(holder.ivCover);

        holder.tvCarName.setText(order.getClient().getCar());
        holder.tvClient.setText(order.getClient().getName());
        holder.tvTime.setText(DateUtils.millisToPattern(order.getDate(), DateUtils.PATTERN_HOUR_MIN));
//        holder.tvNumber.setText(order.getClient().getCar());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
