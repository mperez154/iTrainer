package com.bignerdranch.android.itrainer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mperez5 on 9/8/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<DataProvider> arrayList = new ArrayList<DataProvider>();

    //Constructor for RecyclerAdapter class
    public RecyclerAdapter(ArrayList<DataProvider> arrayList)
    {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        DataProvider dataProvider = arrayList.get(position);
        holder.imageView.setImageResource(dataProvider.getImg_res());
        holder.c_name.setText(dataProvider.getC_name());
        holder.userName.setText(dataProvider.getUserName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        //Declare variables of type ImageView and TextView
        ImageView imageView;
        TextView c_name, userName;

        public RecyclerViewHolder(View view)
        {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.img);
            c_name = (TextView) view.findViewById(R.id.fName_ItemLayout);
            userName = (TextView) view.findViewById(R.id.lName_ItemLayout);
        }

    }
}
