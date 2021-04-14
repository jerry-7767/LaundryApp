package com.example.laundryapp.FRAGMENT;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryapp.ADAPTER.WashFoldAdapter;
import com.example.laundryapp.POJO.SelectClothsPojo;
import com.example.laundryapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OthersFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private WashFoldAdapter washFoldAdapter;
    private EditText search_edtxt;
    private ImageView clear_edtxt_imgvw;
    public static ArrayList<SelectClothsPojo> selectClothsPojos;
    private DatabaseReference databaseReference;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_others,container,false);

        recyclerView = view.findViewById(R.id.recyclervw_select_others_cloths);
        search_edtxt = view.findViewById(R.id.edtxt_item_other_search_input);
        clear_edtxt_imgvw = view.findViewById(R.id.item_clear_others_icon);
        progressBar = view.findViewById(R.id.progress_bar_others);

        search_edtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    washFoldAdapter.getFilter().filter(s);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        clear_edtxt_imgvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_edtxt.setText("");
            }
        });

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        selectClothsPojos = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("WFHouseholdCloths");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    progressBar.setVisibility(View.INVISIBLE);
                    SelectClothsPojo selectClothsPojo = dataSnapshot.getValue(SelectClothsPojo.class);
                    selectClothsPojos.add(selectClothsPojo);
                }
                washFoldAdapter = new WashFoldAdapter(selectClothsPojos,getContext());
                recyclerView.setAdapter(washFoldAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
