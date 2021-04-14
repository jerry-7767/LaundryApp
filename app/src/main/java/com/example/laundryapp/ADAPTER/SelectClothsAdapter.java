//package com.example.laundryapp.ADAPTER;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.laundryapp.FRAGMENT.MenFragment;
//import com.example.laundryapp.POJO.LaundryCatgPojo;
//import com.example.laundryapp.POJO.SelectClothsPojo;
//import com.example.laundryapp.POJO.SignupPojo;
//import com.example.laundryapp.R;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//
//public class SelectClothsAdapter extends RecyclerView.Adapter<SelectClothsAdapter.SelctclthsViewHolder> {
//
//    private LayoutInflater inflater;
//    private Context ctx;
//
//    public SelectClothsAdapter(Context ctx) {
//
//        inflater = LayoutInflater.from(ctx);
//        this.ctx = ctx;
//    }
//
//    @NonNull
//    @Override
//    public SelctclthsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_add_cloths_layout,parent,false);
//        SelctclthsViewHolder selctclthsViewHolder = new SelctclthsViewHolder(view);
//        return selctclthsViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SelctclthsViewHolder holder, int position) {
//        holder.clthsname.setText(MenFragment.selectClothsPojos.get(position).getClthsname());
//        holder.quantity.setText(String.valueOf(MenFragment.selectClothsPojos.get(position).getQuantity()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return MenFragment.selectClothsPojos.size();
//    }
//
//    public class SelctclthsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        public ImageView plus, minus;
//        public TextView quantity, clthsname;
//        public SelctclthsViewHolder(@NonNull View itemView) {
//            super(itemView);
//            plus = itemView.findViewById(R.id.imgvw_slct_clths_add);
//            minus = itemView.findViewById(R.id.imgvw_slct_clths_minus);
//            quantity = itemView.findViewById(R.id.txtvw_quantity);
//            clthsname = itemView.findViewById(R.id.txtvw_cloths_type);
//
//            plus.setTag(R.integer.btn_plus_view, itemView);
//            minus.setTag(R.integer.btn_minus_view, itemView);
//            plus.setOnClickListener(this);
//            minus.setOnClickListener(this);
//        }
//        // onClick Listener for view
//        @Override
//        public void onClick(View v) {
//
//            if (v.getId() == plus.getId()){
//                View tempview = (View) plus.getTag(R.integer.btn_plus_view);
//                TextView tv = (TextView) tempview.findViewById(R.id.txtvw_quantity);
//                int number = Integer.parseInt(tv.getText().toString()) + 1;
//                tv.setText(String.valueOf(number));
//                MenFragment.selectClothsPojos.get(getAdapterPosition()).setQuantity(number);
//
//                String saveCurrentTime, saveCurrentDate;
//                Calendar calendar = Calendar.getInstance();
//                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
//                saveCurrentDate = currentDate.format(calendar.getTime());
//
//                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//                saveCurrentTime = currentTime.format(calendar.getTime());
//
//                final DatabaseReference cartlistRef = FirebaseDatabase.getInstance().getReference().child("Cartlist");
//                final HashMap<String, Object> cartMap = new HashMap<>();
////                cartMap.put("clthName",clthsname.getText().toString());
//                cartMap.put("cl",MenFragment.selectClothsPojos.get(getAdapterPosition()).getClthsname());
//                cartMap.put("cq",MenFragment.selectClothsPojos.get(getAdapterPosition()).getQuantity());
////                cartMap.put("quantity",tv.getText().toString());
//                cartMap.put("date",saveCurrentDate);
//                cartMap.put("time",saveCurrentTime);
//                cartlistRef.child("uView").child("allCloths").updateChildren(cartMap)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()){
//                                    cartlistRef.child("aView").child("allCloths").updateChildren(cartMap)
//                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    Toast.makeText(ctx, "Added to cart", Toast.LENGTH_SHORT).show();
//                                                }
//                                            });
//                                }
//                            }
//                        });
//
//            } else if(v.getId() == minus.getId()) {
//
//                View tempview = (View) minus.getTag(R.integer.btn_minus_view);
//                TextView tv = (TextView) tempview.findViewById(R.id.txtvw_quantity);
//                int number = Integer.parseInt(tv.getText().toString()) - 1;
//                tv.setText(String.valueOf(number));
//                MenFragment.selectClothsPojos.get(getAdapterPosition()).setQuantity(number);
//            }
//        }
//    }
//}