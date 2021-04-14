package com.example.laundryapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.laundryapp.DATA.PaymentOptionsData;
import com.example.laundryapp.DATA.WashFoldDatabaseHelper;
import com.example.laundryapp.FRAGMENT.LoadingFragment;
import com.example.laundryapp.POJO.PaymentPojo;
import com.example.laundryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import static com.example.laundryapp.ADAPTER.RadioGridLayout.final_time;
import static com.example.laundryapp.FRAGMENT.OrderFragment.final_all_item_price;
import static com.example.laundryapp.FRAGMENT.OrderFragment.final_grandtotal;
import static com.example.laundryapp.FRAGMENT.OrderFragment.timestamp;
import static com.example.laundryapp.UI.DateTimeActivity.final_date;
import static com.example.laundryapp.UI.LocationActivity.final_address;

public class PaymentActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private RecyclerView payment_recyclerview;
    private ArrayList<PaymentPojo> paymentPojos;
//    public PaymentAdapter paymentAdapter;
    private RelativeLayout confirmbtn;
//    private ArrayList<CartPojo> cartPojos;
//    public static String clthname,clthcatgname,clthquantity,clthprice,clthtotalprice;
    private WashFoldDatabaseHelper washFoldDatabaseHelper;
    private RadioGroup payment_options;
    public static String final_payment;
    private TextView txt_amount_payable,txt_all_item_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        confirmbtn = findViewById(R.id.btn_next_order);
        payment_options = findViewById(R.id.radiogrp_payment);
        txt_amount_payable = findViewById(R.id.txtvw_final_show_amount_payable);
        txt_all_item_price = findViewById(R.id.txtvw_final_show_total_item_price);

        txt_amount_payable.setText(final_grandtotal);
        txt_all_item_price.setText(final_all_item_price);

        washFoldDatabaseHelper = new WashFoldDatabaseHelper(this);

        mAuth = FirebaseAuth.getInstance();
//        payment_recyclerview = findViewById(R.id.recyclervw_payment_options);
        paymentPojos = new ArrayList<>();
        for (int i = 0; i< PaymentOptionsData.paymentimages.length; i++){
            paymentPojos.add(new PaymentPojo(
                    PaymentOptionsData.paymenttype[i],
                    PaymentOptionsData.paymentimages[i]
            ));
        }

        payment_options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedPayment = payment_options.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedPayment);
                Toast.makeText(PaymentActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                final_payment = radioButton.getText().toString();
            }
        });

//        payment_recyclerview.setLayoutManager(new LinearLayoutManager(PaymentActivity.this, LinearLayoutManager.VERTICAL, false));
//        paymentAdapter = new PaymentAdapter(PaymentActivity.this,paymentPojos);
//        payment_recyclerview.setAdapter(paymentAdapter);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showProgressDialog();

//                washFoldDatabaseHelper.deleteAllData();
//
//                String saveCurrentTime, saveCurrentDate;
//                Calendar calendar = Calendar.getInstance();
//                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
//                saveCurrentDate = currentDate.format(calendar.getTime());
//
//                timestamp = "LNDRY"+System.currentTimeMillis();
//
//                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//                saveCurrentTime = currentTime.format(calendar.getTime());
//
//                String final_cost = final_grandtotal;
//
//                String order_address = final_address;
//                String order_date = final_date;
//                String order_time = final_time;
//                String order_payment = final_payment;
//
//                final HashMap<String, Object> cartMap = new HashMap<>();
//                cartMap.put("orderBy",""+mAuth.getUid());
//                cartMap.put("orderId",""+timestamp);
//                cartMap.put("orderTime",""+saveCurrentTime);
//                cartMap.put("orderDate",saveCurrentDate);
//                cartMap.put("orderStatus","In Progress");
//                cartMap.put("orderCost",""+final_cost);
//
//                cartMap.put("deliveryAddress",order_address);
//                cartMap.put("deliveryDate",order_date);
//                cartMap.put("deliveryTime",order_time);
//                cartMap.put("deliveryPaymentMethod",order_payment);
//
//                DatabaseReference cartlistRef = FirebaseDatabase.getInstance().getReference("User").child("Orders");
//                cartlistRef.child(timestamp).child("Items").setValue(cartMap)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                dismissProgressDialog();
//                                Toast.makeText(PaymentActivity.this, "successfull!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PaymentActivity.this,ConfirmActivity.class);
                                startActivity(intent);
                                finish();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(PaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

            }
        });
    }

    private void showProgressDialog(){
        LoadingFragment loadingFragment = new LoadingFragment();
        loadingFragment.newInstance().show(getSupportFragmentManager(),"");
    }

    private void dismissProgressDialog(){
        Fragment prev = getSupportFragmentManager().findFragmentByTag("fragment_dialog");
        if (prev != null) {
            LoadingFragment df = (LoadingFragment) prev;
            df.dismiss();
        }
    }
}


