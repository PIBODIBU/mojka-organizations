package com.mojka.organizations.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mojka.organizations.R;
import com.rey.material.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_cover)
    public ImageView ivCover;

    @BindView(R.id.tv_car_name)
    public TextView tvCarName;

    @BindView(R.id.btn_call)
    public Button btnCall;

    @BindView(R.id.tv_number)
    public TextView tvNumber;

    @BindView(R.id.tv_client)
    public TextView tvClient;

    @BindView(R.id.tv_time)
    public TextView tvTime;

    @BindView(R.id.btn_repeat_order)
    public Button btnRepeatOrder;

    public OrderHistoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
