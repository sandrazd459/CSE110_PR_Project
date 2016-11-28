package org.example.blog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alan Beas on 11/6/2016.
 */

public class List_Adapter extends RecyclerView.Adapter<List_Holder> implements Filterable{

    Context c;
    ArrayList<Post> mList,mFilterList;
    List_CustomFilter filtr;

    public List_Adapter(Context ctx, ArrayList<Post> mList){
        this.c = ctx;
        this.mList = mList;
        this.mFilterList = mList;

    }

    @Override
    public List_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list, parent ,false);

        List_Holder newHolder = new List_Holder(v);

        return newHolder;
    }

    @Override
    public void onBindViewHolder(List_Holder holder, final int pos) {

        //binding data
        holder.setFrom(mList.get(pos).getStart());
        holder.setTo(mList.get(pos).getDestination());
        holder.setDate(mList.get(pos).getStringDate());
        holder.setPrice(mList.get(pos).getPrice());

        //on item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent pop = new Intent(c, Pop_window.class);
                bundle.putString("uid", mList.get(pos).getUid());
                bundle.putString("start",mList.get(pos).getStart());
                bundle.putString("dest",mList.get(pos).getDestination());
                bundle.putString("price",mList.get(pos).getPrice());
                bundle.putString("addit",mList.get(pos).getAdditional());
                bundle.putString("date", mList.get(pos).getStringDate());
                pop.putExtras(bundle);
                c.startActivity(pop);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        if(filtr==null){
            filtr = new List_CustomFilter(this, mFilterList);
        }
        return filtr;
    }

    public String stringMonth(int m){
        switch (m){
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Oct";
            case 9:
                return "Aug";
            case 10:
                return "Sep";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return "";
    }
}
