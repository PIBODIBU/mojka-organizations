package com.mojka.organizations.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.mojka.organizations.R;

import java.util.HashMap;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    protected Toolbar toolbar;
    private ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fabric.with(this, new Crashlytics());

        if (useDataBinding())
            binding = DataBindingUtil.setContentView(this, getLayoutId());
        else
            setContentView(getLayoutId());

        ButterKnife.bind(this);
        fetchToolbar();
    }

    protected Boolean useDataBinding() {
        return false;
    }

    protected ViewDataBinding getBinding() {
        return binding;
    }

    private void setupBottomNavigationSelection(BottomNavigationView bottomNavigationView) {
        HashMap<String, Integer> items = new HashMap<>();

//        items.put(ProfileActivity.class.getName(), R.id.item_list);
//        items.put(MapActivity.class.getName(), R.id.item_map);
//        items.put(ProfileActivity.class.getName(), R.id.item_profile);

        bottomNavigationView.setSelectedItemId(items.get(BaseActivity.this.getClass().getName()));
    }

    private void fetchToolbar() {
        toolbar = findViewById(R.id.toolbar);

        if (toolbar == null)
            return;

        setupToolbar(toolbar);
    }

    private void setupToolbar(Toolbar toolbar) {
        TextView tvTitle = toolbar.findViewById(R.id.tv_title);
        tvTitle.setText(getActivityTitle());

        ImageButton ibClose = toolbar.findViewById(R.id.ib_close);
        if (getOnCloseButtonListener() != null)
            ibClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnCloseButtonListener().onclick();
                }
            });
        if (!showCloseButton())
            ibClose.setVisibility(View.GONE);
        else
            ibClose.setVisibility(View.VISIBLE);
    }

    protected void setToolbarTitle(@StringRes int stringId) {
        TextView tvTitle = toolbar.findViewById(R.id.tv_title);
        tvTitle.setText(stringId);
    }

    protected abstract int getLayoutId();

    public abstract String getActivityTitle();

    protected Boolean showCloseButton() {
        return true;
    }

    protected Boolean attachBottomNavigation() {
        return false;
    }

    protected abstract OnCloseButtonListener getOnCloseButtonListener();

    public interface OnCloseButtonListener {
        void onclick();
    }
}
