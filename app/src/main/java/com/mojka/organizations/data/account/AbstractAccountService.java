package com.mojka.organizations.data.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.mojka.organizations.data.model.User;

public abstract class AbstractAccountService {
    private final String SHARED_PREFERENCES_NAME = "com.mojka.poisk.account";

    private static final String KEY_ID = "KEY_ID";
    private static final String KEY_NAME = "KEY_NAME";
    private static final String KEY_PHONE = "KEY_PHONE";
    private static final String KEY_EMAIL = "KEY_EMAIL";
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String KEY_DESCRIPTION = "KEY_DESCRIPTION";
    private static final String KEY_CITY = "KEY_CITY";

    private Context context;
    private SharedPreferences sharedPreferences;

    public AbstractAccountService(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setAccount(@NonNull User user) {
        sharedPreferences.edit()
                .putString(KEY_NAME, user.getName())
                .putString(KEY_PHONE, user.getPhone())
                .putString(KEY_EMAIL, user.getEmail())
                .putString(KEY_TOKEN, user.getToken())
                .putString(KEY_DESCRIPTION, user.getDescription())
                .putString(KEY_CITY, user.getCity())
                .apply();
    }

    @NonNull
    public User getAccount() {
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_PHONE, ""),
                sharedPreferences.getString(KEY_NAME, ""),
                sharedPreferences.getString(KEY_EMAIL, ""),
                sharedPreferences.getString(KEY_TOKEN, ""),
                sharedPreferences.getString(KEY_DESCRIPTION, ""),
                sharedPreferences.getString(KEY_CITY, "")
        );
    }

    @NonNull
    public String getToken() {
        if (!isLogged())
            return "";

        return getAccount().getToken();
    }

    public void setParam(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void setParam(String key, Integer value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public Boolean isLogged() {
        return !sharedPreferences.getString(KEY_TOKEN, "").equals("");
    }

    public void logout() {
        sharedPreferences.edit().clear().apply();
    }
}
