package org.example.blog;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by Alan Beas on 11/6/2016.
 */

public class List_CustomFilter extends Filter {

    List_Adapter mAdapter;
    ArrayList<Post> mFilterList;

    public List_CustomFilter(List_Adapter adapter, ArrayList<Post> filterlist){
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
            ArrayList<Post> filterResults = new ArrayList<>();

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
        mAdapter.mList = (ArrayList<Post>) results.values;

        //refresh the page
        mAdapter.notifyDataSetChanged();
    }
}
