package com.example.laundryapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundryapp.ADAPTER.AddressAdapter;
import com.example.laundryapp.DATA.AddressDatabaseHelper;
import com.example.laundryapp.POJO.AddressPojo;
import com.example.laundryapp.POJO.CartPojo;
import com.example.laundryapp.R;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<AddressPojo> addressPojo;
    private TextView txtvw_emptyview;
    private AddressAdapter addressAdapter;
    private AddressDatabaseHelper databaseHelper;
    private RelativeLayout add_address_btn;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        databaseHelper = new AddressDatabaseHelper(this);
        txtvw_emptyview = findViewById(R.id.txtvw_no_address);
        recyclerView = findViewById(R.id.recyclervw_address);
        add_address_btn = findViewById(R.id.btn_add_address);
        addressPojo = new ArrayList<>();

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        showrecycelrview();

        if (addressPojo.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            txtvw_emptyview.setVisibility(View.VISIBLE);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            txtvw_emptyview.setVisibility(View.GONE);
        }

        add_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this,AddAddressActivity.class);
                startActivity(intent);
                finish();
            }
        });

        enableSwipeToDeleteAndUndo();
    }
    //show data in recyclerview from database
    public void showrecycelrview()
    {
        try {
            addressPojo = databaseHelper.getAlldata();
            addressAdapter = new AddressAdapter(this, addressPojo);
            recyclerView.setAdapter(addressAdapter);
            addressAdapter.notifyDataSetChanged();

            Log.d("TAG", "populateListView: Displaying data in list view");
        }catch (Exception e)
        {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipetoDelete swipetoDelete = new SwipetoDelete(AddressActivity.this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final AddressPojo item = addressAdapter.getData().get(position);
                addressAdapter.deleteItem(position);
//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
//                snackbar.setAction("UNDO", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        SwipetoDelete.restoreItem(item, position);
//                        recyclerView.scrollToPosition(position);
//                    }
//                });
//                snackbar.setActionTextColor(Color.YELLOW);
//                snackbar.show();
            }
        };
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipetoDelete);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }
}