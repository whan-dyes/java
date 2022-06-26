package com.whan.passbyvalue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.whan.lib.Person;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends  AppCompatActivity implements View.OnClickListener {
    private Button btn_second;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        textView=findViewById(R.id.textView);
        btn_second=findViewById(R.id.btn_second);
        btn_second.setOnClickListener(this);

        Intent i=getIntent();
        textView.setText(i.getStringExtra("data"));


    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(SecondActivity.this, MainActivity.class);
        switch (view.getId()) {
            case R.id.btn_second:
//                i.putExtra("data","从第二个页面返回的数据");
                EventBus.getDefault().postSticky(new Person("zhangsan",18));
                EventBus.getDefault().postSticky("Event bus");
                startActivity(i);
                break;
        }
    }
}
