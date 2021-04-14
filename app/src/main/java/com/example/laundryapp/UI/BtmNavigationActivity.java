package com.example.laundryapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.laundryapp.FRAGMENT.HomeFragment;
import com.example.laundryapp.FRAGMENT.OrderFragment;
import com.example.laundryapp.FRAGMENT.ProfileFragment;
import com.example.laundryapp.FRAGMENT.StatusFragment;
import com.example.laundryapp.R;
import java.util.ArrayDeque;
import java.util.Deque;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class BtmNavigationActivity extends AppCompatActivity {

//    private BottomNavigationView bNavigationview;
    private MeowBottomNavigation bNavigationview;
    private FragmentManager fragmentManager;
    private Deque<Integer> deque = new ArrayDeque<>(4);
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btm_navigation);

        bNavigationview = findViewById(R.id.btm_navigation_view);
        deque.push(R.id.nav_home);
        loadFragment(new HomeFragment());

//        bNavigationview.setSelectedItemId(R.id.nav_home);
//        bNavigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if (deque.contains(id)){
//                    if (id==R.id.nav_home){
//                        if (deque.size()!=1){
//                            if (flag){
//                                deque.addFirst(R.id.nav_home);
//                                flag=false;
//                            }
//                        }
//                    }
//                    deque.remove(id);
//                }
//                deque.push(id);
//                loadFragment(getFragment(item.getItemId()));
//                return true;
//            }
//        });

        bNavigationview.add(new MeowBottomNavigation.Model(1,R.drawable.ic_outline_home_24));
        bNavigationview.add(new MeowBottomNavigation.Model(2,R.drawable.ic_outline_receipt_long_24));
        bNavigationview.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_access_time_24));
        bNavigationview.add(new MeowBottomNavigation.Model(4,R.drawable.ic_outline_account_circle_24));

        bNavigationview.show(1,true);
        bNavigationview.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new HomeFragment());
                        break;
                    case 2:
                        replace(new OrderFragment());
                        break;
                    case 3:
                        replace(new StatusFragment());
                        break;
                    case 4:
                        replace(new ProfileFragment());
                        break;

                }
                return null;
            }
        });
    }

    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_frame,fragment);
        fragmentTransaction.commit();
    }

//    private Fragment getFragment(int itemId){
//        switch (itemId){
//            case R.id.nav_home:
//                bNavigationview.getModelById(1);
////                        getMenu().getItem(0).setChecked(true);
//                return new HomeFragment();
//            case R.id.nav_order:
//                bNavigationview.getModelById(2);
////                getMenu().getItem(1).setChecked(true);
//                return new OrderFragment();
//            case R.id.nav_status:
//                bNavigationview.getModelById(3);
////                        getMenu().getItem(2).setChecked(true);
//                return new StatusFragment();
//            case R.id.nav_profile:
//                bNavigationview.getModelById(4);
////                        getMenu().getItem(3).setChecked(true);
//                return new ProfileFragment();
//        }
//        bNavigationview.getModelById(1);
////                .getMenu().getItem(0).setChecked(true);
//        return new HomeFragment();
//    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().
                replace(R.id.layout_frame,fragment,fragment.getClass().getSimpleName()).commit();
    }
//    @Override
//    public void onBackPressed() {
//        deque.pop();
//        if (!deque.isEmpty()){
//            loadFragment(getFragment(deque.peek()));
//        }else {
//            finish();
//        }
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}