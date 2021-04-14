package com.example.laundryapp.SearchF;

import android.widget.Filter;

import com.example.laundryapp.ADAPTER.IronAdapter;
import com.example.laundryapp.ADAPTER.WashIronAdapter;
import com.example.laundryapp.POJO.SelectClothsPojo;

import java.util.ArrayList;

public class SearchWashIron extends Filter {

    private WashIronAdapter washIronAdapter;
    private ArrayList<SelectClothsPojo> selectClothsPojos;

    public SearchWashIron(WashIronAdapter washIronAdapter, ArrayList<SelectClothsPojo> selectClothsPojos) {
        this.washIronAdapter = washIronAdapter;
        this.selectClothsPojos = selectClothsPojos;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint!=null && constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<SelectClothsPojo> model = new ArrayList<>();
            for (int i=0;i<selectClothsPojos.size(); i++){
                if (selectClothsPojos.get(i).getClthname().toUpperCase().contains(constraint)){
                    model.add(selectClothsPojos.get(i));
                }
            }
            results.count = model.size();
            results.values = model;
        }else {
            results.count = selectClothsPojos.size();
            results.values = selectClothsPojos;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        washIronAdapter.selectClothsPojos = (ArrayList<SelectClothsPojo>) results.values;
        washIronAdapter.notifyDataSetChanged();
    }
}
