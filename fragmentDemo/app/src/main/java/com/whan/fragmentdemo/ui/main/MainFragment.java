package com.whan.fragmentdemo.ui.main;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.whan.fragmentdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private Button btn_blank;
    private TextView tv;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        tv = root.findViewById(R.id.message);
        EventBus.getDefault().register(this);

        btn_blank = root.findViewById(R.id.btn_blank);
        btn_blank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedInstanceState == null) {
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.container, BlankFragment.newInstance("a", "b"))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return root;
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.POSTING)
    public void onEvent(String str) {
        tv.setText(str);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}