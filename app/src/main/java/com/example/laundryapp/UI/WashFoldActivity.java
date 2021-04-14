package com.example.laundryapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.laundryapp.ADAPTER.CouponAdapter;
import com.example.laundryapp.DATA.CouponimgData;
import com.example.laundryapp.FRAGMENT.KidsFragment;
import com.example.laundryapp.FRAGMENT.MenFragment;
import com.example.laundryapp.FRAGMENT.OrderFragment;
import com.example.laundryapp.FRAGMENT.OthersFragment;
import com.example.laundryapp.FRAGMENT.WomenFragment;
import com.example.laundryapp.POJO.CouponPojo;
import com.example.laundryapp.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Timer;
import java.util.TimerTask;

public class WashFoldActivity extends AppCompatActivity implements View.OnClickListener{

    private RadioButton menbtn;
    private RadioButton womenbtn;
    private RadioButton kidsbtn;
    private RadioButton othersbtn;
    private ArrayList<CouponPojo> couponPojos;
    private CouponAdapter couponAdapter;
    private TextView nametxt;
    private ViewPager coupon_viewPager;
    private int currentPage = 0;
    private int NUM_PAGES = 4;
    private DotsIndicator dotsIndicator;
    private Timer timer;
    private final long Delay_ms = 500;//delay in milliseconds before task is to be executed
    private final long Period_ms = 2500;//time in milliseconds between successive task executions
    private Deque<Integer> deque = new ArrayDeque<>(4);
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_fold);

        menbtn = findViewById(R.id.radio_men_btn);
        womenbtn = findViewById(R.id.radio_women_btn);
        kidsbtn = findViewById(R.id.radio_kids_btn);
        othersbtn = findViewById(R.id.radio_others_btn);
        nametxt = findViewById(R.id.txtvw_name_categry);
        dotsIndicator = findViewById(R.id.dots_indicator);

        menbtn.setOnClickListener(this);
        womenbtn.setOnClickListener(this);
        kidsbtn.setOnClickListener(this);
        othersbtn.setOnClickListener(this);

        String name = getIntent().getStringExtra("catname0");
        nametxt.setText("Choose the types of cloths for "+name);

        deque.push(R.id.radio_men_btn);
        replaceFragment(new MenFragment());
        menbtn.setChecked(true);

        coupon_viewPager = findViewById(R.id.viewpager_coupon);

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
            case R.id.radio_men_btn:
                fragment = new MenFragment();
                replaceFragment(fragment);
                break;

            case R.id.radio_women_btn:
                fragment = new WomenFragment();
                replaceFragment(fragment);
                break;

            case R.id.radio_kids_btn:
                fragment = new KidsFragment();
                replaceFragment(fragment);
                break;

            case R.id.radio_others_btn:
                fragment = new OthersFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_category,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private Fragment getFragment(int itemId){
        switch (itemId){
            case R.id.radio_men_btn:
                menbtn.setChecked(true);
                return new MenFragment();
            case R.id.radio_women_btn:
                womenbtn.setChecked(true);
                return new WomenFragment();
            case R.id.radio_kids_btn:
                kidsbtn.setChecked(true);
                return new KidsFragment();
            case R.id.radio_others_btn:
                othersbtn.setChecked(true);
                return new OthersFragment();
        }
        menbtn.setChecked(true);
        return new MenFragment();
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