package org.example.blog;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by Alan Beas on 11/6/2016.
 */

public class Request_CustomFilter extends Filter {

    Requests_Adapter mAdapter;
    ArrayList<Blog> mFilterList;

    public Request_CustomFilter(Requests_Adapter adapter, ArrayList<Blog> filterlist){
        this.mAdapter = adapter;
        this.mFilterList = filterlist;

    }
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults results = new FilterResults();

        //Check input constraint

        if(constraint != null && constraint.length() > 0){
            constraint = constraint.toString().toUpperCase();

            //store the results of the filtering
            ArrayList<Blog> filterResults = new ArrayList<>();

            for(int i=0; i<mFilterList.size();i++){
                if(mFilterList.get(i).getDestination().toUpperCase().contains(constraint)){
                    filterResults.add(mFilterList.get(i));
                }
            }

            results.count = filterResults.size();
            results.values = filterResults;

        }
        else{
            results.count = mFilterList.size();
            results.values = mFilterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        mAdapter.mList = (ArrayList<Blog>) results.values;

        //refresh the page
        mAdapter.notifyDataSetChanged();
    }
}
