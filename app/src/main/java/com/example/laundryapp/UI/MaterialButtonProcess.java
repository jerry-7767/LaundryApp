package com.example.laundryapp.UI;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.laundryapp.R;

public class MaterialButtonProcess {
    private CardView button_style_parent;
    private RelativeLayout button_click_parent;
    private ImageView button_icon;
    private ProgressBar button_progress;
    private TextView button_text;

    public MaterialButtonProcess(View view) {
        setType(view);
    }

    private void setType(View view) {
        button_style_parent = view.findViewById(R.id.button_style_parent);
        button_click_parent = view.findViewById(R.id.btn_schedule_pickup);
        button_progress = view.findViewById(R.id.button_progress);
        button_text = view.findViewById(R.id.button_text);
    }

    public RelativeLayout getButton() {
        return button_click_parent;
    }

    public void setText(String text) {
        button_text.setText(text);
    }

    public void setBackgroundColor(String color) {
        button_style_parent.setCardBackgroundColor(Color.parseColor(color));
    }

    public void setVectorIcon(int iconId) {
        button_icon.setImageResource(iconId);
    }

    public void setRadiusPixel(int radius) {
        button_style_parent.setRadius(dpToPixel(radius));
    }

    private int dpToPixel(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void setProgressVisibility(boolean isVisible) {
        if (isVisible) {
            button_progress.setVisibility(View.VISIBLE);
        } else {
            button_progress.setVisibility(View.GONE);
        }
    }

    public void setProgressColor(String color) {
        button_progress.getIndeterminateDrawable().setColorFilter(Color.parseColor(color), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    public void setIconColor(String color) {
        button_icon.setColorFilter(Color.parseColor(color), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public void setTextColor(String color){
        button_text.setTextColor(Color.parseColor(color));
    }
}
