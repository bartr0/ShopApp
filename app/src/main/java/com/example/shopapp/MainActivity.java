package com.example.shopapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        modelList = new ArrayList<>();
        modelList.add(new Model("Nothing Phone 1", getString(R.string.phone1), "470$", R.drawable.phone ));
        modelList.add(new Model("OnePlus Nord", getString(R.string.phone2), "300$", R.drawable.phone));
        modelList.add(new Model("OnePlus 10", getString(R.string.phone3), "699$", R.drawable.phone));
        modelList.add(new Model("Samsung S20 FE", getString(R.string.phone4), "550$", R.drawable.phone));
        modelList.add(new Model("Nokia 3310", getString(R.string.phone5), "50$", R.drawable.phone));
        modelList.add(new Model("Pixel 6 Pro", getString(R.string.phone8), "699$", R.drawable.phone));
        modelList.add(new Model("Vertu Ferrari Edition", getString(R.string.phone9), "999$", R.drawable.phone));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        mAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);

    }
}