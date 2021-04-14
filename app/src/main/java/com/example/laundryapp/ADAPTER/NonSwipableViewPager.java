package com.example.laundryapp.ADAPTER;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

public class NonSwipableViewPager extends ViewPager {

    private boolean isPagingEnabled = true;

    public NonSwipableViewPager(@NonNull Context context) {
        super(context);
//        setMyScroller();
    }

    public NonSwipableViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        setMyScroller();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled && super.onTouchEvent(ev);
    }

//    private void setMyScroller() {
//        try{
//            Class<?> viewpager = ViewPager.class;
//            Field scroller = viewpager.getDeclaredField("mScroller");
//            scroller.setAccessible(true);
//            scroller.set(this, new MyScroller(getContext()));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    private class MyScroller extends Scroller {
//        public MyScroller(Context context) {
//            super(context, new DecelerateInterpolator());
//        }
//
//        @Override
//        public void startScroll(int startX, int startY, int dx, int dy) {
//            super.startScroll(startX, startY, dx, dy, 350);
//        }
//    }

    public void setPagingEnabled(boolean b){
        this.isPagingEnabled = b;
    }
}
