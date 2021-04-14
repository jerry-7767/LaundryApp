package com.example.laundryapp.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryapp.ADAPTER.LaundryCatgAdapter;
import com.example.laundryapp.DATA.LaundryCatgData;
import com.example.laundryapp.POJO.LaundryCatgPojo;
import com.example.laundryapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
//     implements NavigationView.OnNavigationItemSelectedListener
    public View view;
    private RecyclerView catg_recyclerView;
    private RelativeLayout menu_card;
    private GridLayoutManager gridLayoutManager;
    private LaundryCatgAdapter laundryCatgAdapter;
    private ArrayList<LaundryCatgPojo> laundryCatgPojos;
    private DrawerLayout drawerLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container, false);

        int columnNo = 2;
        catg_recyclerView = view.findViewById(R.id.recyclervw_laundry_catg);
//        setNavigationViewListener();
//        drawerLayout = view.findViewById(R.id.drawer_layout);
//        menu_card = view.findViewById(R.id.btn_menu);

//        menu_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                    drawerLayout.openDrawer(GravityCompat.START);
//                }else {
//                    drawerLayout.closeDrawer(Gravity.LEFT);
//                }
//            }
//        });

        gridLayoutManager = new GridLayoutManager(getContext(), columnNo);
        catg_recyclerView.setLayoutManager(gridLayoutManager);

        laundryCatgPojos = new ArrayList<>();
        for (int i=0; i<LaundryCatgData.laundrycatname.length; i++){
            laundryCatgPojos.add(new LaundryCatgPojo(
                    LaundryCatgData.laundrycatimage[i],
                    LaundryCatgData.laundrycatname[i]
            ));
        }

        laundryCatgAdapter = new LaundryCatgAdapter(getActivity(), laundryCatgPojos);
        catg_recyclerView.setAdapter(laundryCatgAdapter);

        return view;
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.side_home) {
//            drawerLayout.closeDrawer(Gravity.LEFT);
//        } else if (id == R.id.side_orders) {
//            Toast.makeText(getActivity(), "Order Click", Toast.LENGTH_SHORT).show();
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//    private void setNavigationViewListener() {
//        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//    }
}
