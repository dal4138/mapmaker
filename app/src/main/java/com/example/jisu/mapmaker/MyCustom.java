package com.example.jisu.mapmaker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustom extends RecyclerView.Adapter<MyCustom.CustomViewHolder>  {
    private int layout;
    private ArrayList<Data> list=new ArrayList<>();
    static int number=0;
    public MyCustom(int layout, ArrayList<Data> list) {
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);
        CustomViewHolder viewHolder= new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, final int i) {
        customViewHolder.layouttextview.setText(list.get(i).getName());
    }

    @Override
    public int getItemCount() {return (list != null) ? list.size() : 0; }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView layouttextview;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            layouttextview=itemView.findViewById(R.id.layouttextview);
        }
    }
}
