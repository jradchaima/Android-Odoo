package com.example.venteproductapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{
    ArrayList<ProductData>  data;
    Context context;

    public ProductAdapter(ArrayList<ProductData> data, Product activity) {
        this.data = data;
        this.context = activity;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {        final ProductData productlist = data.get(position);
        byte[] imageBytes;
        holder.Name.setText(productlist.getName());
        holder.Price.setText(productlist.getPrice());
        imageBytes = Base64.decode(productlist.getImage(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.Image.setImageBitmap(decodedImage);



    }



    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Name;
        TextView Price;
        ImageView Image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.userName);
           Price= itemView.findViewById(R.id.userDesc);
           Image = itemView.findViewById(R.id.userImage);

        } }

}


