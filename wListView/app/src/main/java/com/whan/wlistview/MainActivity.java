package com.whan.wlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lstv_test;
    private TextView text_1;

//    private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango", "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView.LOG_TAG;
        
        text_1 = findViewById(R.id.text_1);

        lstv_test = findViewById(R.id.lstv_test);
        List<Fruit> fruitList=new ArrayList<>();
        for (int i = 0; i <15 ; i++) {
            Fruit pineapple=new Fruit(R.drawable.ic_launcher_background,"pine","$16.9");
            fruitList.add(pineapple);

            Fruit pineapple4=new Fruit(R.drawable.ic_launcher_background,"44pine","$4416.9");
            fruitList.add(pineapple4);

            Fruit pineapple3=new Fruit(R.drawable.ic_launcher_background,"33pine","$3316.9");
            fruitList.add(pineapple3);

//            Fruit pineapple2=new Fruit(R.drawable.ic_launcher_foreground,"22pine","$2216.9");
//            fruitList.add(pineapple2);
//
//            Fruit pineapple1=new Fruit(R.drawable.ic_launcher_foreground,"11pine","$1116.9");
//            fruitList.add(pineapple1);
        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, data);
       FruitAdapter adapter=new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        lstv_test.setAdapter(adapter);

        lstv_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String str= (String) adapterView.getItemAtPosition(i);
//                text_1.setText(i+":"+str);
                Fruit fruit=fruitList.get(i);
                text_1.setText(i+" : "+fruit.getName()+":"+fruit.getPrice());
            }
        });
    }
}