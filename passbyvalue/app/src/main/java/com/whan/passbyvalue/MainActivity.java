package com.whan.passbyvalue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whan.lib.Person;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int count = 0;
    private Button btn_main, btn_listView;
    private TextView textView, tv_eventbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        textView = findViewById(R.id.textView);
        tv_eventbus = findViewById(R.id.tv_eventbus);

        btn_main = findViewById(R.id.btn_main);
        btn_main.setOnClickListener(this);

        btn_listView = findViewById(R.id.btn_listView);
        btn_listView.setOnClickListener(this);

//        Intent i=getIntent();
//
//        textView.setText(i.getStringExtra("data"));
//        if (textView.getText().length()==0){
//            textView.setText("Main ******");
//        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.ASYNC)
    public void onEvent(String str) {
        textView.setText(str + count + "");
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.ASYNC)
    public void onMessageEvent(Person person) {
        tv_eventbus.setText(person.getName() + ":" + person.getAge());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_main:
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("data", "来自MainActivity::hello second...");
                startActivity(i);
                break;

            case R.id.btn_listView:
                Intent i1 = new Intent(MainActivity.this, ListViewDemo.class);
//                i.putExtra("data", "来自MainActivity::hello second...");
                startActivity(i1);
                break;
        }
    }

        @Override
        protected void onResume() {
            count++;
            super.onResume();
        }

        @Override
        protected void onDestroy () {
            super.onDestroy();
            EventBus.getDefault().unregister(this);
        }
    }