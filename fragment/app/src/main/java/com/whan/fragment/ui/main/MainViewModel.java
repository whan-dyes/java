package com.whan.fragment.ui.main;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
   int counter=0;

    public MainViewModel(int counter) {
        this.counter = counter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
    // TODO: Implement the ViewModel
}