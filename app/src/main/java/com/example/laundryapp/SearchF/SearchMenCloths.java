package com.example.laundryapp.SearchF;

import android.widget.Filter;

import com.example.laundryapp.ADAPTER.WashFoldAdapter;
import com.example.laundryapp.POJO.SelectClothsPojo;

import java.util.ArrayList;

public class SearchMenCloths extends Filter {

    private WashFoldAdapter washFoldAdapter;
    private ArrayList<SelectClothsPojo> selectClothsPojos;

    public SearchMenCloths(WashFoldAdapter washFoldAdapter, ArrayList<SelectClothsPojo> selectClothsPojos) {
        this.washFoldAdapter = washFoldAdapter;
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
        washFoldAdapter.selectClothsPojos = (ArrayList<SelectClothsPojo>) results.values;
        washFoldAdapter.notifyDataSetChanged();
    }
}
