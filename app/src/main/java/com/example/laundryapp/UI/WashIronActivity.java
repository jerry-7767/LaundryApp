package com.example.laundryapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.laundryapp.ADAPTER.CouponAdapter;
import com.example.laundryapp.DATA.CouponimgData;
import com.example.laundryapp.FRAGMENT.KidsFragmentWashIron;
import com.example.laundryapp.FRAGMENT.MenFragmentWashIron;
import com.example.laundryapp.FRAGMENT.OthersFragmentWashIron;
import com.example.laundryapp.FRAGMENT.WomenFragmentWashIron;
import com.example.laundryapp.POJO.CouponPojo;
import com.example.laundryapp.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Timer;
import java.util.TimerTask;

public class WashIronActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView nametxt;
    private RadioButton menbtn,womenbtn,kidsbtn,othersbtn;
    private ArrayList<CouponPojo> couponPojos;
    private CouponAdapter couponAdapter;
    private ViewPager coupon_viewPager;
    private int currentPage = 0;
    private int NUM_PAGES = 4;
    private Timer timer;
    private final long Delay_ms = 500;//delay in milliseconds before task is to be executed
    private final long Period_ms = 2500;//time in milliseconds between successive task executions
    private Deque<Integer> deque = new ArrayDeque<>(4);
    private boolean flag = true;
    private DotsIndicator dotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_iron);

        menbtn = findViewById(R.id.radio_men_btn_wash_iron);
        womenbtn = findViewById(R.id.radio_women_btn_wash_iron);
        kidsbtn = findViewById(R.id.radio_kids_btn_wash_iron);
        othersbtn = findViewById(R.id.radio_others_btn_wash_iron);
        nametxt = findViewById(R.id.txtvw_name_categry_wash_iron);
        dotsIndicator = findViewById(R.id.dots_indicator_wash_iron);

        menbtn.setOnClickListener(this);
        womenbtn.setOnClickListener(this);
        kidsbtn.setOnClickListener(this);
        othersbtn.setOnClickListener(this);

        String name = getIntent().getStringExtra("catname2");
        nametxt.setText("Choose the types of cloths for "+name);

        deque.push(R.id.radio_men_btn_wash_iron);
        replaceFragment(new MenFragmentWashIron());
        menbtn.setChecked(true);

        coupon_viewPager = findViewById(R.id.viewpager_coupon_wash_iron);

        couponPojos = new ArrayList<>();
        for (int i = 0; i< CouponimgData.couponimages.length; i++){
            couponPojos.add(new CouponPojo(
                    CouponimgData.couponimages[i]
            ));
        }

        couponAdapter = new CouponAdapter(this,couponPojos);
        coupon_viewPager.setAdapter(couponAdapter);
        dotsIndicator.setViewPager(coupon_viewPager);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(currentPage == NUM_PAGES-1){
                    currentPage = 0;
                }
                coupon_viewPager.setCurrentItem(currentPage++,true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },Delay_ms,Period_ms);
    }
    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.radio_men_btn_wash_iron:
                fragment = new MenFragmentWashIron();
                replaceFragment(fragment);
                break;

            case R.id.radio_women_btn_wash_iron:
                fragment = new WomenFragmentWashIron();
                replaceFragment(fragment);
                break;

            case R.id.radio_kids_btn_wash_iron:
                fragment = new KidsFragmentWashIron();
                replaceFragment(fragment);
                break;

            case R.id.radio_others_btn_wash_iron:
                fragment = new OthersFragmentWashIron();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_category_wash_iron,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private Fragment getFragment(int itemId){
        switch (itemId){
            case R.id.radio_men_btn_wash_iron:
                menbtn.setChecked(true);
                return new MenFragmentWashIron();
            case R.id.radio_women_btn_wash_iron:
                womenbtn.setChecked(true);
                return new WomenFragmentWashIron();
            case R.id.radio_kids_btn_wash_iron:
                kidsbtn.setChecked(true);
                return new KidsFragmentWashIron();
            case R.id.radio_others_btn_wash_iron:
                othersbtn.setChecked(true);
                return new OthersFragmentWashIron();
        }
        menbtn.setChecked(true);
        return new MenFragmentWashIron();
    }

    @Override
    public void onBackPressed() {
        deque.pop();
        if (!deque.isEmpty()){
            replaceFragment(getFragment(deque.peek()));
        }else {
            finish();
        }
    }
}