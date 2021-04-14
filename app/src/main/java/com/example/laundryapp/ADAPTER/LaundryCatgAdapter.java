package com.example.laundryapp.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryapp.POJO.LaundryCatgPojo;
import com.example.laundryapp.R;
import com.example.laundryapp.UI.DryCleanActivity;
import com.example.laundryapp.UI.IronActivity;
import com.example.laundryapp.UI.WashFoldActivity;
import com.example.laundryapp.UI.WashIronActivity;

import java.util.ArrayList;

public class LaundryCatgAdapter extends RecyclerView.Adapter<LaundryCatgAdapter.LaundryCatgViewHolder> {

    private Context mContext;
    private ArrayList<LaundryCatgPojo> laundryCatgPojos;

    public LaundryCatgAdapter(Context activity, ArrayList<LaundryCatgPojo> laundryCatgPojos) {
        this.mContext = activity;
        this.laundryCatgPojos = laundryCatgPojos;
    }

    @NonNull
    @Override
    public LaundryCatgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_laundry_category, parent, false);
        LaundryCatgViewHolder laundryCatgViewHolder = new LaundryCatgViewHolder(view);
        return laundryCatgViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LaundryCatgViewHolder holder, int position) {
        holder.imageView.setImageResource(laundryCatgPojos.get(position).getImages());
        holder.textView.setText(laundryCatgPojos.get(position).getName());
        setOnclick((holder).cardView,position);
    }

    @Override
    public int getItemCount() {
        return laundryCatgPojos.size();
    }

    public class LaundryCatgViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        CardView cardView;

        public LaundryCatgViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgvw_laundry_catg_image);
            textView = itemView.findViewById(R.id.txtvw_laundry_catg_name);
            cardView = itemView.findViewById(R.id.card_choose_laundry);

        }
    }
    private void setOnclick(final CardView cardView, final int pos){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 0) {
                    Intent intent = new Intent(mContext, WashFoldActivity.class);
                    intent.putExtra("catname0",laundryCatgPojos.get(pos).getName());
                    mContext.startActivity(intent);
                }else if(pos == 1){
                    Intent intent = new Intent(mContext, IronActivity.class);
                    intent.putExtra("catname1",laundryCatgPojos.get(pos).getName());
                    mContext.startActivity(intent);
                }else if(pos == 2){
                    Intent intent = new Intent(mContext, WashIronActivity.class);
                    intent.putExtra("catname2",laundryCatgPojos.get(pos).getName());
                    mContext.startActivity(intent);
                }else if(pos == 3){
                    Intent intent = new Intent(mContext, DryCleanActivity.class);
                    intent.putExtra("catname3",laundryCatgPojos.get(pos).getName());
                    mContext.startActivity(intent);
                }
            }
        });
    }
}
