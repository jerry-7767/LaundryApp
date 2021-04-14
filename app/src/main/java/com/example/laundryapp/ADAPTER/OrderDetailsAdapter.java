package com.example.laundryapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryapp.FRAGMENT.OrderFragment;
import com.example.laundryapp.POJO.CartPojo;
import com.example.laundryapp.R;

import java.util.ArrayList;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder> {

    private Context mContext;
    public ArrayList<CartPojo> cartPojos;

    public OrderDetailsAdapter(Context mContext, ArrayList<CartPojo> cartPojos) {
        this.mContext = mContext;
        this.cartPojos = cartPojos;
    }

    @NonNull
    @Override
    public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_order_details_list_layout,parent,false);
        return new OrderDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {
        CartPojo cartPojo = cartPojos.get(position);
        String clthname_orderlist = cartPojo.getClothname();
        String clthprice_orderlist = cartPojo.getClothprice();
        String clthquantity_orderlist = cartPojo.getClothquantity();
        String clthtotalprice_orderlist = cartPojo.getClothtotalprice();
        String clthcatg_orderlist = cartPojo.getClothcatgname();

        holder.order_list_clth_name.setText(clthname_orderlist);
        holder.order_list_clth_price.setText(clthprice_orderlist);
        holder.order_list_clth_quantity.setText(clthquantity_orderlist);
        holder.order_list_clth_total_price.setText(clthtotalprice_orderlist);
        holder.order_list_clth_catg.setText(clthcatg_orderlist);
    }

    @Override
    public int getItemCount() {
        return cartPojos.size();
    }

    public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {
        private TextView order_list_clth_name;
        private TextView order_list_clth_price;
        public TextView order_list_clth_total_price;
        private TextView order_list_clth_catg;
        private TextView order_list_clth_quantity;

        public OrderDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            order_list_clth_name = itemView.findViewById(R.id.txtvw_clth_name_order_details_list);
            order_list_clth_price = itemView.findViewById(R.id.txtvw_clth_price_order_details_list);
            order_list_clth_total_price = itemView.findViewById(R.id.txtvw_clth_total_price_order_details_list);
            order_list_clth_catg = itemView.findViewById(R.id.txtvw_clth_catg_name_order_details_list);
            order_list_clth_quantity = itemView.findViewById(R.id.txtvw_clth_quantity_order_details_list);
        }
    }

}
