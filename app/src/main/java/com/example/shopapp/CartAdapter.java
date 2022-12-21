package com.example.shopapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.shopapp.Database.OrderContract;

public class CartAdapter extends CursorAdapter {


    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // getting theviews

        TextView phoneName, addonCharger, addonEarbuds, price, quantity;


        phoneName = view.findViewById(R.id.phoneNameCart);
        price = view.findViewById(R.id.phonePriceCart);
        addonCharger = view.findViewById(R.id.chargerCart);
        addonEarbuds = view.findViewById(R.id.earbudsCart);
        quantity = view.findViewById(R.id.quantityCart);

        // getting the values by first getting the position of their columns

        int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int priceofphone = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int quantityofphone = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);
        int extraEarbuds = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_ADDON2);
        int extraCharger = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_ADDON1);



        String nameofphone = cursor.getString(name);
        String pricesofphone = cursor.getString(priceofphone);
        String quantitysofphone = cursor.getString(quantityofphone);
        String addEarbuds = cursor.getString(extraEarbuds);
        String addCharger = cursor.getString(extraCharger);



        phoneName.setText(nameofphone);
        price.setText(pricesofphone);
        addonCharger.setText(addEarbuds);
        addonEarbuds.setText(addCharger);
        quantity.setText(quantitysofphone);





    }
}
