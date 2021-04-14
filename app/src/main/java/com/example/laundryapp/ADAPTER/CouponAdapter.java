package com.example.laundryapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.laundryapp.POJO.CouponPojo;
import com.example.laundryapp.R;

import java.util.ArrayList;


public class CouponAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<CouponPojo> couponPojos;

    public CouponAdapter(Context context, ArrayList<CouponPojo> couponPojos) {
        this.couponPojos = couponPojos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return couponPojos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.single_coupon_layout,container, false);
        ImageView imageView1 = view.findViewById(R.id.imgvw_coupon);
        imageView1.setImageResource(couponPojos.get(position).getCouponimg());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Position:-"+position, Toast.LENGTH_SHORT).show();
            }
        });

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
