package com.mojka.organizations.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mojka.organizations.R;
import com.mojka.organizations.data.account.AccountService;
import com.mojka.organizations.ui.activity.order.ActiveOrderListActivity;
import com.mojka.organizations.ui.activity.order.HistoryOrderListActivity;
import com.mojka.organizations.ui.support.drawer.DrawerDivider;
import com.mojka.organizations.ui.support.drawer.DrawerItem;

import java.util.HashMap;

public abstract class BaseNavDrawerActivity extends BaseActivity {
    private static final String TAG = "BaseNavDrawerActivity";

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected Drawer drawer;
    private HashMap<String, IDrawerItem> drawerItems = new HashMap<>();
    private AccountService accountService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountService = new AccountService(this);
        setupDrawer();
    }

    private void setupToolbar() {
        if (this.toolbar != null) {
            ImageButton ibMenu = toolbar.findViewById(R.id.ib_menu);

            if (ibMenu == null)
                return;

            ibMenu.setVisibility(View.VISIBLE);
            ibMenu.setOnClickListener(v -> drawer.openDrawer());
        }
    }

    protected void setupDrawer() {
        setupToolbar();

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withSliderBackgroundColorRes(R.color.drawer_background)
                .withHeader(R.layout.drawer_header)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .build();


        if (accountService.isLogged()) {
            ((TextView) drawer.getHeader().findViewById(R.id.tv_name)).setText(accountService.getAccount().getName());
        }

        addDrawerItems();
        setDrawerSelection();
    }

    private void addDrawerItems() {
        IDrawerItem itemActiveOrders = new DrawerItem()
                .withOnDrawerItemClickListener(() -> {
                    startActivity(new Intent(BaseNavDrawerActivity.this, ActiveOrderListActivity.class));
                    finish();
                })
                .withTitle("Записи");

        IDrawerItem itemHistoryOrders = new DrawerItem()
                .withOnDrawerItemClickListener(() -> {
                    startActivity(new Intent(BaseNavDrawerActivity.this, HistoryOrderListActivity.class));
                    finish();
                })
                .withTitle("История записей");

        IDrawerItem itemClients = new DrawerItem()
                .withOnDrawerItemClickListener(() -> {
                    startActivity(new Intent(BaseNavDrawerActivity.this, ActiveOrderListActivity.class));
                    finish();
                })
                .withTitle("Клиенты");

        IDrawerItem itemProfile = new DrawerItem()
                .withOnDrawerItemClickListener(() -> {
                    startActivity(new Intent(BaseNavDrawerActivity.this, ActiveOrderListActivity.class));
                    finish();
                })
                .withTitle("Профиль");

        IDrawerItem itemServices = new DrawerItem()
                .withOnDrawerItemClickListener(() -> {
                    startActivity(new Intent(BaseNavDrawerActivity.this, ActiveOrderListActivity.class));
                    finish();
                })
                .withTitle("Услуги");

        IDrawerItem itemExit = new DrawerItem()
                .withOnDrawerItemClickListener(() -> {
                    accountService.logout();
                    startActivity(new Intent(BaseNavDrawerActivity.this, LoginActivity.class));
                    finish();
                })
                .withTitle("Выход");

        IDrawerItem itemRate = new DrawerItem()
                .withOnDrawerItemClickListener(() -> {
                    accountService.logout();
                    startActivity(new Intent(BaseNavDrawerActivity.this, LoginActivity.class));
                    finish();
                })
                .withTitle("Оцените наше приложение");

        drawer.addItem(itemActiveOrders);
        drawerItems.put(ActiveOrderListActivity.class.getName(), itemActiveOrders);

        drawer.addItem(itemHistoryOrders);
        drawerItems.put(HistoryOrderListActivity.class.getName(), itemHistoryOrders);

        drawer.addItem(itemClients);
        drawerItems.put("3", itemClients);

        drawer.addItem(itemProfile);
        drawerItems.put("4", itemProfile);

        drawer.addItem(itemServices);
        drawerItems.put("5", itemServices);

        drawer.addItem(new DrawerDivider());

        drawer.addItem(itemExit);
        drawerItems.put("Exit", itemExit);

        drawer.addItem(itemRate);
        drawerItems.put("Rate", itemRate);
    }

    private void setDrawerSelection() {
        try {
            drawer.setSelection(drawerItems.get(this.getClass().getName()), false);
        } catch (Exception ex) {
            ex.printStackTrace();
            drawer.setSelection(-1);
        }
    }
}