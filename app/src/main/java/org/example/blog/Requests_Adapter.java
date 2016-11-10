package org.example.blog;

import android.content.Context;
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

public class Requests_Adapter extends RecyclerView.Adapter<List_of_Requests.BlogViewHolder> implements Filterable{

    Context c;
    ArrayList<Blog> mList,mFilterList;
    Request_CustomFilter filtr;

    public Requests_Adapter(Context ctx, ArrayList<Blog> mList){
        this.c = ctx;
        this.mList = mList;
        this.mFilterList = mList;

    }

    @Override
    public List_of_Requests.BlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list,null);

        List_of_Requests.BlogViewHolder newHolder = new List_of_Requests.BlogViewHolder(v);
        return null;
    }

    @Override
    public void onBindViewHolder(List_of_Requests.BlogViewHolder holder, int position) {

        holder.mFrom.setText(mList.get(position).getStart());
        holder.mTo.setText(mList.get(position).getDestination());
        String s = mList.get(position).getMonth()+" "+mList.get(position).getDay()+", "+mList.get(position).getYear();
        holder.mDate.setText(s);
        holder.mPric.setText(mList.get(position).getPrice());

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
            filtr = new Request_CustomFilter(this, mFilterList);
        }
        return filtr;
    }
}
