package com.whan.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String DB_NAME = "mydb.db3";
    //    private TextView tvName,tvAge;
    private EditText et_name, et_age;
    private Button bt_add, bt_del, bt_modify, bt_query;
    private ListView listView;
    private dbHelper db;
    private SQLiteDatabase dbRead, dbWrite;
    private SimpleCursorAdapter adapter;
    private int itemId;
    private String itemName, itemAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);

        bt_add = findViewById(R.id.bt_add);
        bt_del = findViewById(R.id.bt_del);
        bt_modify = findViewById(R.id.bt_modify);
        bt_query = findViewById(R.id.bt_query);

//        tvName=findViewById(R.id.tvName);
//        tvAge=findViewById(R.id.tvAge);

        listView = findViewById(R.id.listView);

        bt_add.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_modify.setOnClickListener(this);
        bt_query.setOnClickListener(this);

        db = new dbHelper(this, DB_NAME, null, 1);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

        Cursor cursor = dbRead.query(dbHelper.TB_NAME, null, null,
                null, null, null, null, null);
        adapter = new SimpleCursorAdapter(this,
                R.layout.listview,
                cursor,
                new String[]{"_id", "name", "age"},
                new int[]{R.id.tvID, R.id.tvName, R.id.tvAge},
                0);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor1 = adapter.getCursor();
                cursor1.moveToPosition(i);
                itemId = cursor1.getInt(cursor1.getColumnIndex("_id"));
                itemName = cursor1.getString(cursor1.getColumnIndex("name"));
                itemAge = cursor1.getString(cursor1.getColumnIndex("age"));
                et_name.setText(itemName);
                et_age.setText(itemAge);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                dbAdd();
                refreshListView();
                break;
            case R.id.bt_del:
                dbDel();
                refreshListView();
                break;
            case R.id.bt_modify:
                dbUpdate();
                refreshListView();
                break;
            case R.id.bt_query:
                dbQuery();
//                refreshListView();
                break;
        }
    }

    private void refreshListView() {
        Cursor cursor = dbRead.query(dbHelper.TB_NAME, null, null,
                null, null, null, null, null);
        adapter.changeCursor(cursor);
    }

    protected void dbAdd() {
        for (int i = 0; i <3 ; i++) {
            ContentValues values = new ContentValues();
            values.put("name", et_name.getText().toString().trim());
            values.put("age", et_age.getText().toString().trim());
            long rowid = dbWrite.insert(dbHelper.TB_NAME, null, values);
            if (rowid == -1)
                Log.i("info", "?????????????????????");
            else
                Log.i("info", "??????????????????!" + rowid);
        }
    }

    protected void dbDel() {
        dbWrite.delete(dbHelper.TB_NAME, "_id=?", new String[]{itemId + ""});
    }

    protected void dbUpdate() {
        ContentValues values = new ContentValues();
        values.put("name", et_name.getText().toString().trim());
        values.put("age", et_age.getText().toString().trim());
        int i = dbWrite.update(dbHelper.TB_NAME, values, "_id=?", new String[]{itemId + ""});
        if (i > 0)
            Log.i("myDbDemo", "?????????????????????");
        else
            Log.i("myDbDemo", "???????????????");
    }

    protected void dbQuery() {
        Cursor cursor = dbRead.query(dbHelper.TB_NAME, null, "name=?",
                new String[]{et_name.getText().toString().trim()}, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            @SuppressLint("Range") int p = cursor.getInt(cursor.getColumnIndex("_id"));
            if (p > 0)
                Log.i("info", "?????????" + p);
            else
                Log.i("info", "????????????" + p);

            adapter.changeCursor(cursor);//??????listview
            listView.setSelectionFromTop(5, 0);//listView??????????????????5??????
        } else {
            Toast.makeText(this, "?????????", Toast.LENGTH_SHORT).show();
        }

    }
}