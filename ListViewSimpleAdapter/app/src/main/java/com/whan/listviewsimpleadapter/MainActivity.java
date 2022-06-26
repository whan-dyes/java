package com.whan.listviewsimpleadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_item_info);
        ListView listView = findViewById(R.id.lv_listview);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(),
                R.layout.list_view,
                new String[]{"id", "name", "age", "image"},
                new int[]{R.id.tvId, R.id.tvName, R.id.tvAge, R.id.iv_image});

        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> listItem = (Map<String, Object>) listView.getItemAtPosition(position);
                String str = String.valueOf(listItem.get("id")) + "  " +
                        String.valueOf(listItem.get("name")) + "  " +
                        String.valueOf(listItem.get("age"));
                textView.setText(str);
                /* textView.setText("****"); */
            }
        });
    }


    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        int count = 0;
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", count++);
            map.put("name", "zhang san");
            map.put("age", 28);
            map.put("image", R.mipmap.d1);
            data.add(map);


            Map<String, Object> map1 = new HashMap<>();
            map1.put("id", count++);
            map1.put("name", "zhang san");
            map1.put("age", 28);
            map1.put("image", R.mipmap.d2);
            data.add(map1);

            Map<String, Object> map2 = new HashMap<>();
            map2.put("id", count++);
            map2.put("name", "zhang san");
            map2.put("age", 28);
            map2.put("image", R.mipmap.d3);
            data.add(map2);

        }

        return data;
    }
}