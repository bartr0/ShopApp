package com.example.shopapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shopapp.Database.OrderContract;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Phone1Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ImageView imageView;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, phone_Name, phone_Price, phone_Description;
    CheckBox addCharger, addEarbuds;
    RadioButton white, black;
    Button addtoCart;
    BottomNavigationView nav;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone1);

        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        phone_Name = findViewById(R.id.phoneName_Info);
        phone_Price = findViewById(R.id.phone_Price);
        phone_Description = findViewById(R.id.descriptioninfo);
        addCharger = findViewById(R.id.extraCharger);
        addtoCart = findViewById(R.id.addtocart);
        addEarbuds = findViewById(R.id.extraEarbuds);
        white = findViewById(R.id.whiteRadioButton);
        black = findViewById(R.id.blackRadioButton);
        nav = findViewById(R.id.nav_phones);

        nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Phone1Activity.this, MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.action_cart:
                    Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Phone1Activity.this, SummaryActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.action_profile:
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
            }
            return false;
        });

        white.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    imageView.setImageResource(R.drawable.phone_white);
                } else {
                    imageView.setImageResource(R.drawable.phone);
                }
            }
        });



        phone_Name.setText("Nothing Phone 1");
        phone_Description.setText(R.string.phone1);

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 0){
                    Toast.makeText(Phone1Activity.this, "Select minimum 1 piece", Toast.LENGTH_SHORT).show();
                }
                if (quantity>0){
                    Intent intent = new Intent(Phone1Activity.this, SummaryActivity.class);
                    startActivity(intent);

                    SaveCart();
                }

            }
        });

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basePrice = 470;
                quantity++;
                displayQuantity();
                int phonePrice = basePrice * quantity;
                String setnewPrice = String.valueOf(phonePrice);
                phone_Price.setText(setnewPrice);

                int ifCheckBox = CalculatePrice(addEarbuds, addCharger);
                phone_Price.setText("$ " + ifCheckBox);

            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = 470;
                if (quantity < 1) {
                    Toast.makeText(Phone1Activity.this, "Select minimum 1 piece", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int phonePrice = basePrice * quantity;
                    String setnewPrice = String.valueOf(phonePrice);
                    phone_Price.setText(setnewPrice);

                    int ifCheckBox = CalculatePrice(addEarbuds, addCharger);
                    phone_Price.setText(ifCheckBox + " $");
                }
            }
        });


    }

    private boolean SaveCart() {
        String name = phone_Name.getText().toString();
        String price = phone_Price.getText().toString();
        String quantity = quantitynumber.getText().toString();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(OrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);

        if (addEarbuds.isChecked()) {
            values.put(OrderContract.OrderEntry.COLUMN_ADDON2, "Earbuds: +");
        } else {
            values.put(OrderContract.OrderEntry.COLUMN_ADDON2, "Earbuds: -");

        }

        if (addCharger.isChecked()) {
            values.put(OrderContract.OrderEntry.COLUMN_ADDON1, "Charger: +");
        } else {
            values.put(OrderContract.OrderEntry.COLUMN_ADDON1, "Charger: -");

        }

        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            }
        }

        hasAllRequiredValues = true;
        return hasAllRequiredValues;

    }

    private int CalculatePrice(CheckBox addonEarbuds, CheckBox addonCharger) {

        int basePrice = 470;

        if (addonEarbuds.isChecked()) {
            basePrice = basePrice + 150;
        }

        if (addonCharger.isChecked()) {
            basePrice = basePrice + 35;
        }

        return basePrice * quantity;
    }

    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
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

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);
            int earbuds = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_ADDON2);
            int charger = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_ADDON1);


            String nameofphone = cursor.getString(name);
            String priceofphone = cursor.getString(price);
            String quantityofphone = cursor.getString(quantity);
            String additionalEarbuds = cursor.getString(earbuds);
            String additionalCharger = cursor.getString(charger);

            phone_Name.setText(nameofphone);
            phone_Price.setText(priceofphone);
            quantitynumber.setText(quantityofphone);

        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


        phone_Name.setText("");
        phone_Price.setText("");
        quantitynumber.setText("");

    }
}