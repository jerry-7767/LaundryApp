package com.example.laundryapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryapp.DATA.AddressDatabaseHelper;
import com.example.laundryapp.POJO.AddressPojo;
import com.example.laundryapp.R;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder>{

    public ArrayList<AddressPojo> addressPojos;
    private Context context;
    public static RadioButton radioButton = null;
    private static int lastCheckedpos = 0;

    public AddressAdapter(Context context, ArrayList<AddressPojo> addressPojos) {
        this.addressPojos = addressPojos;
        this.context = context;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_address_layout,parent, false);
        AddressViewHolder addressViewHolder = new AddressViewHolder(view);
        return addressViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        holder.textView_place.setText(addressPojos.get(position).getPlace());
        holder.textView_address.setText(addressPojos.get(position).getAddress());
        holder.radioButton.setChecked(lastCheckedpos == position);
    }

    @Override
    public int getItemCount() {
        return addressPojos.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        TextView textView_place;
        TextView textView_address;
        public RadioButton radioButton;
        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_place = itemView.findViewById(R.id.txtvw_place_name);
            textView_address = itemView.findViewById(R.id.txtvw_address);
            radioButton = itemView.findViewById(R.id.radio_address_select);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastCheckedpos = getAdapterPosition();
                    notifyDataSetChanged();
                    Toast.makeText(context, "you selected"+textView_address.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    //delete item from database
    public void deleteitemfromdatabase(String place, String address)
    {
        AddressDatabaseHelper databaseHelper = new AddressDatabaseHelper(context);
        try {
            databaseHelper.deletedata(place, address);
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteItem (int positon)
    {
        deleteitemfromdatabase(addressPojos.get(positon).getPlace(), addressPojos.get(positon).getAddress());
        addressPojos.remove(positon);
        notifyDataSetChanged();
    }

    public ArrayList<AddressPojo> getData() {
        return addressPojos;
    }
}
