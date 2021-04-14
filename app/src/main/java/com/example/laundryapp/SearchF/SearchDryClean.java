package com.example.laundryapp.SearchF;

import android.widget.Filter;

import com.example.laundryapp.ADAPTER.DrycleanAdapter;
import com.example.laundryapp.ADAPTER.WashIronAdapter;
import com.example.laundryapp.POJO.SelectClothsPojo;

import java.util.ArrayList;

public class SearchDryClean extends Filter {

    private DrycleanAdapter drycleanAdapter;
    private ArrayList<SelectClothsPojo> selectClothsPojos;

    public SearchDryClean(DrycleanAdapter drycleanAdapter, ArrayList<SelectClothsPojo> selectClothsPojos) {
        this.drycleanAdapter = drycleanAdapter;
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
        drycleanAdapter.selectClothsPojos = (ArrayList<SelectClothsPojo>) results.values;
        drycleanAdapter.notifyDataSetChanged();
    }
}
