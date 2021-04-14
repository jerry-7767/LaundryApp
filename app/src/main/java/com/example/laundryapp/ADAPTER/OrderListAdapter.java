package com.example.laundryapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laundryapp.DATA.WashFoldDatabaseHelper;
import com.example.laundryapp.FRAGMENT.OrderFragment;
import com.example.laundryapp.POJO.CartPojo;
import com.example.laundryapp.R;
import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderlistViewholder> {

    private Context mContext;
    public ArrayList<CartPojo> cartPojos;
    private OrderFragment orderFragment;

    public OrderListAdapter(Context mContext, ArrayList<CartPojo> cartPojos) {
        this.mContext = mContext;
        this.cartPojos = cartPojos;
    }

    @NonNull
    @Override
    public OrderlistViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_order_list_layout,parent,false);
        return new OrderlistViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderlistViewholder holder, int position) {
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

    public class OrderlistViewholder extends RecyclerView.ViewHolder {
        private TextView order_list_clth_name;
        private TextView order_list_clth_price;
        public TextView order_list_clth_total_price;
        private TextView order_list_clth_catg;
        private TextView order_list_clth_quantity;
        private ImageView remove;

        public OrderlistViewholder(@NonNull View itemView) {
            super(itemView);
            order_list_clth_name = itemView.findViewById(R.id.txtvw_clth_name_order_list);
            order_list_clth_price = itemView.findViewById(R.id.txtvw_clth_price_order_list);
            order_list_clth_total_price = itemView.findViewById(R.id.txtvw_clth_total_price_order_list);
            order_list_clth_catg = itemView.findViewById(R.id.txtvw_clth_catg_name_order_list);
            order_list_clth_quantity = itemView.findViewById(R.id.txtvw_clth_quantity_order_list);
            remove = itemView.findViewById(R.id.imgvw_removeItem);

//            remove.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final int position = getAdapterPosition();
//                    final CartPojo item = getData().get(position);
//                    deleteItemwashFold(position);
//                }
//            });
        }
    }

//    //delete item from database
//    public void deleteitemwashfold(String clth_name, String clth_per_piece_price,
//                                   String clth_total_price, String clth_quantity,
//                                   String clth_catg_name)
//    {
//        WashFoldDatabaseHelper databaseHelper = new WashFoldDatabaseHelper(mContext);
//        try {
//            databaseHelper.deletedatawf(clth_name, clth_per_piece_price, clth_total_price, clth_quantity, clth_catg_name);
//            Toast.makeText(mContext, "Successfully Deleted", Toast.LENGTH_SHORT).show();
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//            Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
//        }
//
////        int tx = (int) Long.parseLong(orderFragment.totalprice.getText().toString().replace("$",""));
////        long tPricee = tx - Long.parseLong(clth_total_price.replace("$",""));
////        orderFragment.alltotalprice = 0;
////        orderFragment.totalprice.setText("$"+tPricee);
//
////        long tx = Long.parseLong(orderFragment.totalprice.getText().toString().replace("$",""));
//
////            long deliiveryfee = Long.parseLong(String.format("%2f",clth_total_price))-Long.parseLong(String.format("%2f",deli))
////            orderFragment.grandtotalprice.setText("$",);
//
//        orderFragment = new OrderFragment();
//
//        long tx = Long.parseLong(((orderFragment).totalprice.getText().toString().trim().replace("$","")));
//        long tPrice = tx - Long.parseLong(clth_total_price.replace("$",""));
//        (orderFragment).totalprice.setText(""+tPrice);
//    }
//
//    public void deleteItemwashFold (int positon)
//    {
//        deleteitemwashfold(cartPojos.get(positon).getClothname(), cartPojos.get(positon).getClothprice(),
//                cartPojos.get(positon).getClothtotalprice(), cartPojos.get(positon).getClothquantity(),
//                cartPojos.get(positon).getClothcatgname());
//        cartPojos.remove(positon);
//        notifyDataSetChanged();
//        notifyItemChanged(positon);
//    }
//
//    public ArrayList<CartPojo> getData() {
//        return cartPojos;
//    }

//    public void deleteItemwashFold (int positon)
//    {
//        deleteitemwashfold(cartPojos.get(positon).getClothname(), cartPojos.get(positon).getClothprice(),
//                cartPojos.get(positon).getClothtotalprice(), cartPojos.get(positon).getClothquantity(),
//                cartPojos.get(positon).getClothcatgname());
//        cartPojos.remove(positon);
//        notifyDataSetChanged();
//        notifyItemChanged(positon);
//    }

    public ArrayList<CartPojo> getData() {
        return cartPojos;
    }

}
