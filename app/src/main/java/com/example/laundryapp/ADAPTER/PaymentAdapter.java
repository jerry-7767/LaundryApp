//package com.example.laundryapp.ADAPTER;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CompoundButton;
//import android.widget.ImageView;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.FragmentActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.laundryapp.POJO.PaymentPojo;
//import com.example.laundryapp.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {
//
//    public List<PaymentPojo> paymentPojos;
//    private Context context;
//    public static RadioButton radioButton = null;
//    private static int lastCheckedpos = 0;
//    public static String final_payment;
//
//    public PaymentAdapter(Context context, ArrayList<PaymentPojo> paymentPojos) {
//        this.paymentPojos = paymentPojos;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_payment_layout,parent, false);
//        PaymentViewHolder paymentViewHolder = new PaymentViewHolder(view);
//        return paymentViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
//        holder.payment.setText(paymentPojos.get(position).getPaymenttext());
//        holder.imageView.setImageResource(paymentPojos.get(position).getPaymentimage());
//        holder.radioButton.setChecked(lastCheckedpos == position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return paymentPojos.size();
//    }
//
//    public class PaymentViewHolder extends RecyclerView.ViewHolder {
//        TextView payment;
//        ImageView imageView;
//        public RadioButton radioButton;
//        public PaymentViewHolder(@NonNull View itemView) {
//            super(itemView);
//            payment = itemView.findViewById(R.id.txtvw_payment_options_name);
//            imageView = itemView.findViewById(R.id.imgvw_payment);
//            radioButton = itemView.findViewById(R.id.radio_payment_select);
//
//            radioButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    lastCheckedpos = getAdapterPosition();
//                    notifyDataSetChanged();
//                    Toast.makeText(context, "you selected "+payment.getText(), Toast.LENGTH_SHORT).show();
//                    final_payment = payment.getText().toString();
//                }
//            });
//        }
//    }
//}
