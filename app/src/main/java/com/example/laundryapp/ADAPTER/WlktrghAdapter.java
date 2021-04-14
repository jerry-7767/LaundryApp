package com.example.laundryapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.laundryapp.POJO.WlktrghPojo;
import com.example.laundryapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class WlktrghAdapter extends PagerAdapter {

    private Context mContext;
    private List<WlktrghPojo> wlktrghPojos;

    public WlktrghAdapter(Context mContext, List<WlktrghPojo> wlktrghPojos) {
        this.mContext = mContext;
        this.wlktrghPojos = wlktrghPojos;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_wlktrgh_layout, null);

        ImageView imageView = view.findViewById(R.id.img_wlktrgh_image);
        TextView textView = view.findViewById(R.id.txtvw_wlktrgh_title);
        TextView textView1 = view.findViewById(R.id.txtvw_wlktrgh_desc);

        imageView.setImageResource(wlktrghPojos.get(position).getScreenimg());
        textView.setText(wlktrghPojos.get(position).getTitle());
        textView1.setText(wlktrghPojos.get(position).getDesc());

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);
        return view;

//        container.addView(view);
    }

    @Override
    public int getCount() {
        return wlktrghPojos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
