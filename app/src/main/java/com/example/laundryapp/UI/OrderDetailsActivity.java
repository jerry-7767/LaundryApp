package com.example.laundryapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundryapp.ADAPTER.FinalItemListAdapter;
import com.example.laundryapp.ADAPTER.OrderDetailsAdapter;
import com.example.laundryapp.POJO.CartPojo;
import com.example.laundryapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    private String orderId;
    private TextView orderid_txtvw, txtvw_time, txtvw_date, txtvw_address, txtvw_status;
    public ArrayList<CartPojo> cartPojos;
    private OrderDetailsAdapter orderDetailsAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        orderid_txtvw = findViewById(R.id.txtvw_order_details_orderID);
        txtvw_time = findViewById(R.id.txtvw_order_details_time);
        txtvw_date = findViewById(R.id.txtvw_order_details_date);
        txtvw_address = findViewById(R.id.txtvw_order_details_address);
        txtvw_status = findViewById(R.id.txtvw_order_details_status);
        recyclerView = findViewById(R.id.recyclervw_item_details);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");

        orderid_txtvw.setText(orderId);

        loadOrderDetails();
        loadOrderItemDetails();

    }

    private void loadOrderItemDetails() {

        cartPojos = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User_Orders");
        databaseReference.child("Orders").child(orderId).child("Items")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            CartPojo cartPojo = dataSnapshot.getValue(CartPojo.class);
                            cartPojos.add(cartPojo);
                        }
                        orderDetailsAdapter = new OrderDetailsAdapter(OrderDetailsActivity.this,cartPojos);
                        recyclerView.setAdapter(orderDetailsAdapter);
                        orderDetailsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(OrderDetailsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadOrderDetails() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User_Orders");
        reference.child("Orders").child(orderId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String orderDate = ""+snapshot.child("deliveryDate").getValue();
                        String orderTime = ""+snapshot.child("deliveryTime").getValue();
                        String orderAddress = ""+snapshot.child("deliveryAddress").getValue();
                        String orderStatus = ""+snapshot.child("orderStatus").getValue();

                        txtvw_time.setText(orderTime);
                        txtvw_date.setText(orderDate);
                        txtvw_address.setText(orderAddress);
                        txtvw_status.setText(orderStatus);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(OrderDetailsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}










