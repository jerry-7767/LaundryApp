package com.example.laundryapp.FRAGMENT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.laundryapp.ADAPTER.OrderListAdapter;
import com.example.laundryapp.DATA.WashFoldDatabaseHelper;
import com.example.laundryapp.POJO.CartPojo;
import com.example.laundryapp.R;
import com.example.laundryapp.UI.LocationActivity;
import com.example.laundryapp.UI.MaterialButtonProcess;
import com.example.laundryapp.UI.PaymentActivity;
import com.example.laundryapp.UI.SwipetoDelete;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.laundryapp.DATA.WashFoldDatabaseHelper.TABLE_NAME;

public class OrderFragment extends Fragment {

    private View view;
    private RelativeLayout btnschedule;
    private RecyclerView recyclerView;
    private OrderListAdapter orderListAdapter;
    private LinearLayoutManager linearLayoutManager;
    public TextView totalprice,grandtotalprice;
    public int alltotalprice = 0;
    public int grandTOTAL = 0;
    public int deliveryFees = 40;
    public static String final_grandtotal,timestamp,final_all_item_price;
//    public static String final_clthname,final_clthcatgname,final_clthquantity,final_clthprice,final_clthtotalprice;
    private FirebaseAuth mAuth;
    private ArrayList<CartPojo> cartPojos;
//    public static String clthname,clthcatgname,clthquantity,clthprice,clthtotalprice;
    private TextView myCustomView;
    public static WashFoldDatabaseHelper washFoldDatabaseHelper;
    private ProgressBar button_progress;
    int theme = Color.parseColor("#ff0cf6fd");
    int gray = Color.parseColor("#90CBFF");
    public Context mc;

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order,container,false);

        mAuth = FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.recyclervw_order_list);
        totalprice = view.findViewById(R.id.txtvw_total_price_order_list);
        grandtotalprice = view.findViewById(R.id.txtvw_grand_total);
        linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        myCustomView = view.findViewById(R.id.txtvw_empty_view);
        button_progress = view.findViewById(R.id.button_progress);
        btnschedule = view.findViewById(R.id.btn_schedule_pickup);

        final MaterialButtonProcess materialButton = new MaterialButtonProcess(view);
        materialButton.setBackgroundColor(getString(R.color.theme_color));
        materialButton.setRadiusPixel(2);
        materialButton.setProgressColor(getString(R.color.black));
        materialButton.setTextColor(getString(R.color.black));

        materialButton.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialButton.setProgressVisibility(true);


//                for (int i=0; i<cartPojos.size(); i++) {
//                    clthname = cartPojos.get(i).getClothname();
//                    clthcatgname = cartPojos.get(i).getClothcatgname();
//                    clthquantity = cartPojos.get(i).getClothquantity();
//                    clthprice = cartPojos.get(i).getClothprice();
//                    clthtotalprice = cartPojos.get(i).getClothtotalprice();
//                    HashMap<String, String> orderMap = new HashMap<>();
//                    final_clthname = orderMap.put("clthname", clthname);
//                    final_clthcatgname = orderMap.put("clthcatgname", clthcatgname);
//                    final_clthquantity = orderMap.put("clthquantity", clthquantity);
//                    final_clthprice = orderMap.put("clthprice", clthprice);
//                    final_clthtotalprice = orderMap.put("clthtotalprice", clthtotalprice);
////                    cartlistRef.child(timestamp).child("Items").child(clthname).setValue(orderMap);
//                    Toast.makeText(getActivity(), "successfull", Toast.LENGTH_SHORT).show();
//                }


//                timestamp = "LNDRY"+System.currentTimeMillis();
//                final DatabaseReference cartlistRef = FirebaseDatabase.getInstance().getReference("User").child("Orders");
//                cartlistRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        materialButton.setProgressVisibility(false);
//                        for (int i=0; i<cartPojos.size(); i++) {
//                            clthname = cartPojos.get(i).getClothname();
//                            clthcatgname = cartPojos.get(i).getClothcatgname();
//                            clthquantity = cartPojos.get(i).getClothquantity();
//                            clthprice = cartPojos.get(i).getClothprice();
//                            clthtotalprice = cartPojos.get(i).getClothtotalprice();
//                            HashMap<String, String> orderMap = new HashMap<>();
//                            orderMap.put("orderBy",""+mAuth.getUid());
//                            orderMap.put("orderId",timestamp);
//                            orderMap.put("clthname", clthname);
//                            orderMap.put("clthcatgname", clthcatgname);
//                            orderMap.put("clthquantity", clthquantity);
//                            orderMap.put("clthprice", clthprice);
//                            orderMap.put("clthtotalprice", clthtotalprice);
//                            cartlistRef.child(timestamp).child("Items").child(clthname).setValue(orderMap);
//                            Toast.makeText(getContext(), "successfull", Toast.LENGTH_SHORT).show();
//                        }
                        Intent intent = new Intent(getActivity(), LocationActivity.class);
                        startActivity(intent);
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

        washFoldDatabaseHelper = new WashFoldDatabaseHelper(getActivity());
        enableSwipeToDeleteAndUndo();
        populateRecyclerview();

        return view;
    }

    private void populateRecyclerview() {
        cartPojos = new ArrayList<>();
        try {
            cartPojos = getAlldata();
            orderListAdapter = new OrderListAdapter(getContext(),cartPojos);
            recyclerView.setAdapter(orderListAdapter);
            orderListAdapter.notifyDataSetChanged();
        }catch (Exception e)
        {
            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        if (cartPojos.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            myCustomView.setVisibility(View.VISIBLE);
            btnschedule.setEnabled(false);
            btnschedule.setBackgroundColor(gray);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            myCustomView.setVisibility(View.GONE);
            btnschedule.setEnabled(true);
            btnschedule.setBackgroundColor(theme);
        }
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipetoDelete swipetoDelete = new SwipetoDelete(getActivity()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final CartPojo item = orderListAdapter.getData().get(position);
                deleteItemwashFold(position);
            }
        };
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipetoDelete);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    //fetch all data from database
    public ArrayList<CartPojo> getAlldata()
    {
        ArrayList<CartPojo> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = washFoldDatabaseHelper.getReadableDatabase();
        String query ="SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        while (cursor.moveToNext())
        {
            String clthname = cursor.getString(1);
            String clth_per_piece_price = cursor.getString(2);
            String clth_total_price = cursor.getString(3);
            String clth_quantity = cursor.getString(4);
            String clth_catg_name = cursor.getString(5);

            int tPrice = Integer.parseInt(clth_total_price);

            alltotalprice = alltotalprice + tPrice;
                totalprice.setText(""+alltotalprice);
                grandTOTAL = alltotalprice+deliveryFees;
                grandtotalprice.setText(""+grandTOTAL);
                final_grandtotal = grandtotalprice.getText().toString();
            final_all_item_price = totalprice.getText().toString();

            CartPojo dataModel = new CartPojo(clthname, clth_per_piece_price, clth_quantity,clth_catg_name, clth_total_price);
            arrayList.add(dataModel);
        }
        sqLiteDatabase.close();
        return arrayList;
    }




    //delete item from database
    public void deleteitemwashfold(String clth_name, String clth_per_piece_price,
                                   String clth_total_price, String clth_quantity,
                                   String clth_catg_name)
    {
        WashFoldDatabaseHelper databaseHelper = new WashFoldDatabaseHelper(getContext());
        try {
            databaseHelper.deletedatawf(clth_name, clth_per_piece_price, clth_total_price, clth_quantity, clth_catg_name);
            Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }

//        int tx = (int) Long.parseLong(orderFragment.totalprice.getText().toString().replace("$",""));
//        long tPricee = tx - Long.parseLong(clth_total_price.replace("$",""));
//        orderFragment.alltotalprice = 0;
//        orderFragment.totalprice.setText("$"+tPricee);

//        long tx = Long.parseLong(orderFragment.totalprice.getText().toString().replace("$",""));

//            long deliiveryfee = Long.parseLong(String.format("%2f",clth_total_price))-Long.parseLong(String.format("%2f",deli))
//            orderFragment.grandtotalprice.setText("$",);
        long tx = Long.parseLong(totalprice.getText().toString());
        long tp = tx-Long.parseLong(clth_total_price);
        totalprice.setText(""+tp);
        long fp = tp+deliveryFees;
        grandtotalprice.setText(""+fp);

    }

    public void deleteItemwashFold (int positon)
    {
        deleteitemwashfold(cartPojos.get(positon).getClothname(), cartPojos.get(positon).getClothprice(),
                cartPojos.get(positon).getClothtotalprice(), cartPojos.get(positon).getClothquantity(),
                cartPojos.get(positon).getClothcatgname());
        cartPojos.remove(positon);
        orderListAdapter.notifyDataSetChanged();
        orderListAdapter.notifyItemChanged(positon);
    }

}
