package com.whan.readcsv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void test() throws IOException {
        //通过getAssets获取InputStream获取字节流
        InputStream is = getResources().getAssets().open("agu.csv");
//将字节流转换为字符流
        InputStreamReader isr = new InputStreamReader(is, "UTF_8");
//创建带缓冲区的字符流
        BufferedReader bfr = new BufferedReader(isr);
//逐行循环读取文件
        String in = "";
        List<TestBean> beans = new ArrayList<>();
        while ((in = bfr.readLine()) != null) {
//            Log.i("info", in);
            String[] strings = in.split(",");

            TestBean testBean = new TestBean();
            testBean.setCode(strings[0]);
            testBean.setName(strings[1]);
            beans.add(testBean);
        }
        for (TestBean tb : beans) {
            Log.i("info", tb.getCode() + " " + tb.getName());
        }

    }

}
