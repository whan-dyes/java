package com.whan.readcsv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            test();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            test1();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    private void test1() throws IOException, CsvException {
        InputStream is = this.getAssets().open("agu.csv");
        InputStreamReader reader = new InputStreamReader(is, Charset.forName("UTF-8"));
        List<TestBean> beans = new CsvToBeanBuilder<TestBean>(reader)
                .withType(TestBean.class)
                .build()
                .parse();

//        List<String[]> csv = new CSVReader(reader).readAll();
//        for(String[] strings:csv){
//            for(String s:strings){
//                Log.i("info", s);
//            }
//
//        }

    }

    private void test() throws IOException {
        //通过getAssets获取InputStream获取字节流
        InputStream is = getResources().getAssets().open("agu.csv");
//将字节流转换为字符流
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
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


//        String csvfileString = this.getApplicationInfo().dataDir + File.separatorChar + "agu.csv";
//        List<TestBean> beans = new CsvToBeanBuilder<TestBean>(isr)
//                .withType(TestBean.class)
//                .build()
//                .parse();
//        System.out.println(beans);

}
