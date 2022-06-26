package com.whan.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.whan.fragment.ui.main.MainViewModel;

public class Vmfactory implements ViewModelProvider.Factory {
    private int a;

    public Vmfactory(int a) {
        this.a = a;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new MainViewModel(a);
    }


}
