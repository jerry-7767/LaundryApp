package com.example.laundryapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.laundryapp.ADAPTER.WlktrghAdapter;
import com.example.laundryapp.POJO.WlktrghPojo;
import com.example.laundryapp.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class WalkthroughIntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private WlktrghAdapter wlktrghAdapter;
    private DotsIndicator dotsIndicator;
    private List<WlktrghPojo> wlktrghPojos;
    private RelativeLayout nxt_btn, start_btn;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough_intro);

        wlktrghPojos = new ArrayList<>();
        wlktrghPojos.add(new WlktrghPojo("Select Your Service", "Select your service from the list.", R.drawable.firstscreen));
        wlktrghPojos.add(new WlktrghPojo("Pickup & Drop", "We'll pick up & Drop laundry from your Location.", R.drawable.secondscreen));
        wlktrghPojos.add(new WlktrghPojo("Enjoy your Time", "Enjoy you'll receive cleaned and folded clothes.", R.drawable.thirdscreen));

        dotsIndicator = findViewById(R.id.dots_wlktrgh);
        nxt_btn = findViewById(R.id.btn_wlktrgh_nxt);
//        start_btn = findViewById(R.id.btn_wlktrgh_start);
        viewPager = findViewById(R.id.vwpgr_wlktrgh);
        wlktrghAdapter = new WlktrghAdapter(this, wlktrghPojos);
        viewPager.setAdapter(wlktrghAdapter);
        dotsIndicator.setViewPager(viewPager);

        if (restorebeforeData()){
            Intent intent = new Intent(getApplicationContext(), BtmNavigationActivity.class);
            startActivity(intent);
            finish();
        }

        nxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = viewPager.getCurrentItem();
                if (position<wlktrghPojos.size()){
                    position++;
                    viewPager.setCurrentItem(position);
                }
                if (position==wlktrghPojos.size()){
                    loadLastScreen();
                }
            }
        });
    }

    private boolean restorebeforeData(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = sharedPreferences.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;
    }

    private void savePrefData(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    private void loadLastScreen(){
                Intent intent =new Intent(WalkthroughIntroActivity.this, BtmNavigationActivity.class);
                startActivity(intent);
                savePrefData();
                finish();
    }
}