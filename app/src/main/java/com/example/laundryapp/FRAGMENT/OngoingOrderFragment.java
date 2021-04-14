package com.example.laundryapp.FRAGMENT;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.fragment.app.Fragment;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundryapp.ADAPTER.OngoingOrderAdapter;
import com.example.laundryapp.POJO.OngoingOrderPojo;
import com.example.laundryapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OngoingOrderFragment extends Fragment implements View.OnClickListener{

    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<OngoingOrderPojo> ongoingOrderPojos;
    private TextView empty_view;
    private FirebaseAuth mAuth;
    private OngoingOrderAdapter ongoingOrderAdapter;
    private String timestamp;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ongoing_order,container,false);

        recyclerView = view.findViewById(R.id.recyclervw_oo);
        empty_view = view.findViewById(R.id.txtvw_empty_oo_view);
        progressBar = view.findViewById(R.id.progress_ongoing_orders);

        mAuth = FirebaseAuth.getInstance();

        linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadOrders();

//        ongoingOrderPojos = new ArrayList<>();
//        if (ongoingOrderPojos.isEmpty()){
//            recyclerView.setVisibility(View.GONE);
//            empty_view.setVisibility(View.VISIBLE);
//        }else {
//            recyclerView.setVisibility(View.VISIBLE);
//            empty_view.setVisibility(View.GONE);
//        }

        return view;
    }

    private void loadOrders() {

//        .orderByChild("orderBy").equalTo(mAuth.getUid())

        progressBar.setVisibility(View.VISIBLE);

        ongoingOrderPojos = new ArrayList<>();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User_Orders").child("Orders");
                    reference.orderByChild("orderBy").equalTo(mAuth.getUid())
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                        for (DataSnapshot ds: snapshot.getChildren()){
                                            OngoingOrderPojo orderPojo = ds.getValue(OngoingOrderPojo.class);
                                            ongoingOrderPojos.add(orderPojo);
                                        }
                                        ongoingOrderAdapter = new OngoingOrderAdapter(getContext(),ongoingOrderPojos);
                                        recyclerView.setAdapter(ongoingOrderAdapter);
//                                        ongoingOrderAdapter.notifyDataSetChanged();
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

    }

    @Override
    public void onClick(View v) {

    }
}
