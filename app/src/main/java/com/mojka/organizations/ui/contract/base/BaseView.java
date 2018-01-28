package com.mojka.organizations.ui.contract.base;

import android.app.Activity;
import android.content.Context;

public interface BaseView {
    Context getViewContext();

    Activity getViewActivity();

    void setupUi();
}