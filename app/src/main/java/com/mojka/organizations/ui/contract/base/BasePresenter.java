package com.mojka.organizations.ui.contract.base;

public interface BasePresenter<T> {
    void start();

    void setView(T view);
}