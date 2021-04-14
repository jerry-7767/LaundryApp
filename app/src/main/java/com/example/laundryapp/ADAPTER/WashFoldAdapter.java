package com.example.laundryapp.ADAPTER;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laundryapp.DATA.WashFoldDatabaseHelper;
import com.example.laundryapp.POJO.SelectClothsPojo;
import com.example.laundryapp.R;
import com.example.laundryapp.SearchF.SearchMenCloths;
import com.example.laundryapp.UI.WashFoldActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class WashFoldAdapter extends RecyclerView.Adapter<WashFoldAdapter.WshFldViewHolder> implements Filterable {

    private Context mContext;
    public ArrayList<SelectClothsPojo> selectClothsPojos;
    private SearchMenCloths searchMenCloths;
    private long per_price =0;
    private long total_price =0;
    private Dialog builder;
    private int quantity = 1;


    public WashFoldAdapter(ArrayList<SelectClothsPojo> selectClothsPojos, Context context) {
        this.mContext = context;
        this.selectClothsPojos = selectClothsPojos;
    }

    @NonNull
    @Override
    public WshFldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vieww = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_add_cloths_layout,parent,false);
        builder = new Dialog(mContext);
        return new WshFldViewHolder(vieww);
    }

    @Override
    public void onBindViewHolder(@NonNull WshFldViewHolder holder, int position) {
      final SelectClothsPojo pojo = selectClothsPojos.get(position);
        String clthname = pojo.getClthname();
        String clthprice = String.valueOf(pojo.getClthprice());
        String clthimg = pojo.getClthimage();

        holder.txt_clthname.setText(clthname);
        holder.txt_price.setText(clthprice);
        Glide.with(mContext).load(clthimg).placeholder(R.drawable.tshirt).into(holder.img_clth);

        holder.img_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(pojo);
            }
        });
    }

    private void showDialog(SelectClothsPojo model) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.single_addtocrt_dialog,null);
        builder.setContentView(R.layout.single_addtocrt_dialog);
        ImageView clthimg = builder.findViewById(R.id.imgvw_dialog_clth_image);
        TextView clthname = builder.findViewById(R.id.txtvw_clth_dialog_name);
        TextView clthperprice = builder.findViewById(R.id.txtvw_clth_dialog_perpiece_price);
        TextView clthtotalprice = builder.findViewById(R.id.txtvw_clth_dialog_total_price);
        ImageView incrbtn = builder.findViewById(R.id.imgvw_decrement_quantity);
        ImageView decrbtn = builder.findViewById(R.id.imgvw_increment_quantity);
        TextView quantitytext = builder.findViewById(R.id.txtvw_quantity);
        TextView catg_name = builder.findViewById(R.id.txtvw_dialog_catg_name);
        Button addbagbtn = builder.findViewById(R.id.btn_add_to_bag);

        //get data
        String c_image = model.getClthimage();
        String c_name = model.getClthname();
        String c_per_price = String.valueOf(model.getClthprice());
        per_price = Long.parseLong(c_per_price.replaceAll("$",""));
        total_price = Long.parseLong(c_per_price.replaceAll("$",""));
        quantity = 1;

//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        builder.setView(view);

        Glide.with(mContext).load(c_image).placeholder(R.drawable.tshirt).into(clthimg);
        clthname.setText(""+c_name);
        quantitytext.setText(""+quantity);
        clthperprice.setText(""+per_price);
        clthtotalprice.setText(""+total_price);

//        AlertDialog dialog = builder.create();
        builder.show();

        incrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_price = total_price + per_price;
                quantity++;
                clthtotalprice.setText(""+total_price);
                quantitytext.setText(""+quantity);
            }
        });

        decrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity>1){
                    total_price = total_price - per_price;
                    quantity--;
                    clthtotalprice.setText(""+total_price);
                    quantitytext.setText(""+quantity);
                }
            }
        });

        addbagbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f_c_name = clthname.getText().toString().trim();
//                String f_c_per_price = clthperprice.getText().toString().trim().replace("","");
                String f_c_per_price = clthperprice.getText().toString().trim().replace("$","");
                String f_c_total_price = clthtotalprice.getText().toString().trim().replace("$","");
                String f_c_quantity = quantitytext.getText().toString().trim();
                String f_c_c_name = catg_name.getText().toString().trim();

                String saveCurrentTime, saveCurrentDate;
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calendar.getTime());


                //add to sqlite database
                WashFoldDatabaseHelper washFoldDatabaseHelper = new WashFoldDatabaseHelper(mContext);
                washFoldDatabaseHelper.insertdata(f_c_name,f_c_per_price,f_c_total_price,f_c_quantity,f_c_c_name);
                builder.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectClothsPojos.size();
    }

    @Override
    public Filter getFilter() {
        if (searchMenCloths==null){
            searchMenCloths = new SearchMenCloths(this,selectClothsPojos);
        }
        return searchMenCloths;
    }

    public class WshFldViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_clth,img_bag;
        private TextView txt_clthname,txt_price;
        public WshFldViewHolder(@NonNull View itemView) {
            super(itemView);
            img_clth = itemView.findViewById(R.id.imgvw_clth_type);
            img_bag = itemView.findViewById(R.id.imgvw_add_to_cart);
            txt_clthname = itemView.findViewById(R.id.txtvw_clth_name);
            txt_price = itemView.findViewById(R.id.txtvw_clth_price);
        }
    }
}
