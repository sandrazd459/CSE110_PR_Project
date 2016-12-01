package org.example.blog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

/**
 * Created by Alan Beas on 11/6/2016.
 */

public class ListAdapter extends RecyclerView.Adapter<ListHolder> implements Filterable{

    Context c;
    ArrayList<Post> mList,mFilterList;
    ListCustomFilter filter;

    public ListAdapter(Context ctx, ArrayList<Post> mList){
        this.c = ctx;
        this.mList = mList;
        this.mFilterList = mList;

    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list, parent ,false);

        ListHolder newHolder = new ListHolder(v);

        return newHolder;
    }

    @Override
    public void onBindViewHolder(ListHolder holder, final int pos) {

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
                Intent pop = new Intent(c, DetailedPost.class);
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
        if(filter == null){
            filter = new ListCustomFilter(this, mFilterList);
        }
        return filter;
    }
}
