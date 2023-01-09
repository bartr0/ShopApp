package com.example.shopapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapp.Database.OrderContract;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SummaryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public CartAdapter mAdapter;
    public static final int LOADER = 0;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        nav = findViewById(R.id.nav_summary);

        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SummaryActivity.this, MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.action_cart:
                    Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SummaryActivity.this, SummaryActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.action_profile:
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
            }
            return false;
        });


        Button clearthedata = findViewById(R.id.clearthedatabase);
        clearthedata.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int deletethedata = getContentResolver().delete(OrderContract.OrderEntry.CONTENT_URI, null, null);
            }
        });

        getLoaderManager().initLoader(LOADER, null, this);

        ListView listView = findViewById(R.id.list);
        mAdapter = new CartAdapter(this, null);
        listView.setAdapter(mAdapter);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY,
                OrderContract.OrderEntry.COLUMN_ADDON2,
                OrderContract.OrderEntry.COLUMN_ADDON1
        };

        return new CursorLoader(this, OrderContract.OrderEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mAdapter.swapCursor(null);
    }
}