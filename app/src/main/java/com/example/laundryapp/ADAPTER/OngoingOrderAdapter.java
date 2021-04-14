package com.example.laundryapp.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryapp.DATA.WashFoldDatabaseHelper;
import com.example.laundryapp.FRAGMENT.OrderFragment;
import com.example.laundryapp.POJO.CartPojo;
import com.example.laundryapp.POJO.OngoingOrderPojo;
import com.example.laundryapp.R;
import com.example.laundryapp.UI.OrderDetailsActivity;

import java.util.ArrayList;

public class OngoingOrderAdapter extends RecyclerView.Adapter<OngoingOrderAdapter.OOVIewHolder> {

    private Context mContext;
    public ArrayList<OngoingOrderPojo> ongoingOrderPojos;

    public OngoingOrderAdapter(Context mContext, ArrayList<OngoingOrderPojo> ongoingOrderPojos) {
        this.mContext = mContext;
        this.ongoingOrderPojos = ongoingOrderPojos;
    }

    @NonNull
    @Override
    public OOVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_ongoing_order_list_layout,parent,false);
        return new OOVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OOVIewHolder holder, int position) {
        OngoingOrderPojo orderPojo = ongoingOrderPojos.get(position);
        String ord_id = orderPojo.getOrderId();
        String ord_date = orderPojo.getDeliveryDate();
        String ord_time = orderPojo.getDeliveryTime();
        String ord_status = orderPojo.getOrderStatus();
        String ord_cost = orderPojo.getOrderCost();

        holder.oo_id.setText(ord_id);
        holder.oo_date.setText(ord_date);
        holder.oo_time.setText(ord_time);
        holder.oo_status.setText(ord_status);
        holder.oo_cost.setText(ord_cost);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetailsActivity.class);
                intent.putExtra("orderId",ord_id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ongoingOrderPojos.size();
    }

    public class OOVIewHolder extends RecyclerView.ViewHolder {
        private TextView oo_id;
        private TextView oo_date;
        public TextView oo_time;
        private TextView oo_status;
        private TextView oo_cost;

        public OOVIewHolder(@NonNull View itemView) {
            super(itemView);
            oo_id = itemView.findViewById(R.id.txtvw_oo_order_id);
            oo_date = itemView.findViewById(R.id.txtvw_oo_pickup_date);
            oo_time = itemView.findViewById(R.id.txtvw_oo_pickup_time);
            oo_status = itemView.findViewById(R.id.txtvw_oo_order_status);
            oo_cost = itemView.findViewById(R.id.txtvw_oo_total_price);
        }
    }
}
