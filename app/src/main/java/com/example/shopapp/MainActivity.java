package com.example.shopapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav = findViewById(R.id.nav);

        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.action_cart:
                    Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, SummaryActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.action_profile:
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
            }
            return false;
        });

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