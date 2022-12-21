package com.example.shopapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<Model> modelList;
    Context context;

    public OrderAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        String nameofPhone = modelList.get(position).getmPhoneName();
        String descriptionofPhone = modelList.get(position).getmPhoneDescription();
        String priceofphone = modelList.get(position).getmPhonePrice();
        int images = modelList.get(position).getmPhoneImage();

        holder.mPhoneName.setText(nameofPhone);
        holder.mPhoneDescription.setText(descriptionofPhone);
        holder.mPhonePrice.setText(priceofphone);
        holder.imageView.setImageResource(images);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mPhoneName, mPhoneDescription, mPhonePrice;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mPhoneName = itemView.findViewById(R.id.phoneName);
            mPhoneDescription = itemView.findViewById(R.id.description);
            mPhonePrice = itemView.findViewById(R.id.phonePriceList);
            imageView = itemView.findViewById(R.id.phoneImage);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            if (position == 0) {
                Intent intent = new Intent(context, Phone1Activity.class);
                context.startActivity(intent);
            }

            if (position == 1) {
                Intent intent2 = new Intent(context, Phone2Activity.class);
                context.startActivity(intent2);
            }
            if (position == 2) {
                Intent intent3 = new Intent(context, Phone3Activity.class);
                context.startActivity(intent3);
            }
            if (position == 3) {
                Intent intent2 = new Intent(context, Phone2Activity.class);
                context.startActivity(intent2);
            }
            if (position == 4) {
                Intent intent2 = new Intent(context, Phone2Activity.class);
                context.startActivity(intent2);
            }
            if (position == 5) {
                Intent intent2 = new Intent(context, Phone2Activity.class);
                context.startActivity(intent2);
            }
            if (position == 6) {
                Intent intent2 = new Intent(context, Phone2Activity.class);
                context.startActivity(intent2);
            }
            if (position == 7) {
                Intent intent2 = new Intent(context, Phone2Activity.class);
                context.startActivity(intent2);
            }
            if (position == 8) {
                Intent intent2 = new Intent(context, Phone2Activity.class);
                context.startActivity(intent2);
            }
            if (position == 9) {
                Intent intent2 = new Intent(context, Phone2Activity.class);
                context.startActivity(intent2);
            }
        }
    }
}

