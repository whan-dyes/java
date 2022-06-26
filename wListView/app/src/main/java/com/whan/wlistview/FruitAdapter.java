package com.whan.wlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    public FruitAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit=getItem(position);

        View view= LayoutInflater.from(getContext()).inflate(R.layout.fruit_item,parent,false);

        ImageView fruitimage=view.findViewById(R.id.fruit_image);
        TextView fruitname=view.findViewById(R.id.fruit_name);
        TextView fruitprice=view.findViewById(R.id.fruit_price);

        fruitimage.setImageResource(fruit.getImageID());
        fruitname.setText(fruit.getName());
        fruitprice.setText(fruit.getPrice());
        return view;
    }
}
