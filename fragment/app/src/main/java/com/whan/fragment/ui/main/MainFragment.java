package com.whan.fragment.ui.main;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.whan.fragment.R;
import com.whan.fragment.Vmfactory;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView tv_counter;
    private TextView tv_counter2;
    private Button btn_another;
    private int counter2;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        tv_counter = root.findViewById(R.id.tv_counter);
        tv_counter2 = root.findViewById(R.id.tv_counter2);
        btn_another = root.findViewById(R.id.btn_another);

        root.findViewById(R.id.btn_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.counter++;
                counter2 += 2;
                refreshCounter();
            }

        });

        btn_another.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, new AnotherFragment())
                        .commit();
            }
        });

        return root;
    }

    private void refreshCounter() {
        tv_counter.setText(String.valueOf(mViewModel.counter));
        tv_counter2.setText(String.valueOf(counter2));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new Vmfactory(100)).get(MainViewModel.class);
        // TODO: Use the ViewModel
        refreshCounter();

    }


}