package com.example.laundryapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundryapp.ADAPTER.FinalItemListAdapter;
import com.example.laundryapp.ADAPTER.OrderListAdapter;
import com.example.laundryapp.DATA.WashFoldDatabaseHelper;
import com.example.laundryapp.FRAGMENT.LoadingFragment;
import com.example.laundryapp.FRAGMENT.OrderFragment;
import com.example.laundryapp.POJO.CartPojo;
import com.example.laundryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static com.example.laundryapp.ADAPTER.RadioGridLayout.final_time;
import static com.example.laundryapp.DATA.WashFoldDatabaseHelper.TABLE_NAME;
import static com.example.laundryapp.FRAGMENT.OrderFragment.final_grandtotal;
import static com.example.laundryapp.UI.DateTimeActivity.final_date;
import static com.example.laundryapp.UI.LocationActivity.final_address;
import static com.example.laundryapp.UI.PaymentActivity.final_payment;

public class ConfirmActivity extends AppCompatActivity {

    private MaterialButton btn_confirm;
    private RecyclerView recyclerView;
    private FinalItemListAdapter finalItemListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<CartPojo> cartPojos;
    private OrderFragment orderFragment;
    public static String final_clthname,final_clthcatgname,final_clthquantity,final_clthprice,final_clthtotalprice;
    public static String clthname,clthcatgname,clthquantity,clthprice,clthtotalprice;
    public static String timestamp;
    private FirebaseAuth mAuth;
    private WashFoldDatabaseHelper washFoldDatabaseHelper;
    private TextView txt_final_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        mAuth = FirebaseAuth.getInstance();
        washFoldDatabaseHelper = new WashFoldDatabaseHelper(this);
        orderFragment = new OrderFragment();

        btn_confirm = findViewById(R.id.btn_confirm_order);
        txt_final_amount = findViewById(R.id.txtvw_final_total_amount);
        recyclerView = findViewById(R.id.recyclervw_final_item_details);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        txt_final_amount.setText(final_grandtotal);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showProgressDialog();


                washFoldDatabaseHelper.deleteAllData();

                String saveCurrentTime, saveCurrentDate;
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());

                timestamp = "LNDRY"+System.currentTimeMillis();

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calendar.getTime());

                String final_cost = final_grandtotal;

                String order_address = final_address;
                String order_date = final_date;
                String order_time = final_time;
                String order_payment = final_payment;

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

//                DatabaseReference cartlistRef = FirebaseDatabase.getInstance().getReference("User").child("Orders");
//                cartlistRef.child(timestamp).child("Items").setValue(cartMap)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                dismissProgressDialog();
//                                Toast.makeText(PaymentActivity.this, "successfull!!", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(PaymentActivity.this,ConfirmActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(PaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

                timestamp = "LNDRY"+System.currentTimeMillis();

                HashMap<String, String> orderMap = new HashMap<>();
                orderMap.put("orderTime",""+saveCurrentTime);
                orderMap.put("orderBy",""+mAuth.getUid());
                orderMap.put("orderDate",saveCurrentDate);
                orderMap.put("orderStatus","In Progress");
                orderMap.put("orderCost",""+final_cost);
                orderMap.put("orderId",timestamp);

                orderMap.put("deliveryAddress",order_address);
                orderMap.put("deliveryDate",order_date);
                orderMap.put("deliveryTime",order_time);
                orderMap.put("deliveryPaymentMethod",order_payment);

                final DatabaseReference cartlistRef = FirebaseDatabase.getInstance().getReference("User_Orders").child("Orders");
                cartlistRef.child(timestamp).setValue(orderMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                dismissProgressDialog();
                                for (int i=0; i<cartPojos.size(); i++) {
                                    clthname = cartPojos.get(i).getClothname();
                                    clthcatgname = cartPojos.get(i).getClothcatgname();
                                    clthquantity = cartPojos.get(i).getClothquantity();
                                    clthprice = cartPojos.get(i).getClothprice();
                                    clthtotalprice = cartPojos.get(i).getClothtotalprice();
                                    HashMap<String, String> itemMap = new HashMap<>();
                                    itemMap.put("orderedId",timestamp);
                                    itemMap.put("clthname", clthname);
                                    itemMap.put("clthcatgname", clthcatgname);
                                    itemMap.put("clthquantity", clthquantity);
                                    itemMap.put("clthprice", clthprice);
                                    itemMap.put("clthtotalprice", clthtotalprice);
                                    cartlistRef.child(timestamp).child("Items").child(clthname).setValue(itemMap);
                                    Toast.makeText(ConfirmActivity.this, "successfull", Toast.LENGTH_SHORT).show();
                                }
                                Intent intent = new Intent(ConfirmActivity.this, BtmNavigationActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ConfirmActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


//                cartlistRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (int i=0; i<cartPojos.size(); i++) {
//                            clthname = cartPojos.get(i).getClothname();
//                            clthcatgname = cartPojos.get(i).getClothcatgname();
//                            clthquantity = cartPojos.get(i).getClothquantity();
//                            clthprice = cartPojos.get(i).getClothprice();
//                            clthtotalprice = cartPojos.get(i).getClothtotalprice();
//                            HashMap<String, String> itemMap = new HashMap<>();
//                            itemMap.put("orderBy",""+mAuth.getUid());
//                            itemMap.put("orderId",timestamp);
//                            itemMap.put("clthname", clthname);
//                            itemMap.put("clthcatgname", clthcatgname);
//                            itemMap.put("clthquantity", clthquantity);
//                            itemMap.put("clthprice", clthprice);
//                            itemMap.put("clthtotalprice", clthtotalprice);
//
////                            orderMap.put("orderBy",""+mAuth.getUid());
////                            orderMap.put("orderId",""+timestamp);
////                            orderMap.put("orderTime",""+saveCurrentTime);
////                            orderMap.put("orderDate",saveCurrentDate);
////                            orderMap.put("orderStatus","In Progress");
////                            orderMap.put("orderCost",""+final_cost);
////
////                            orderMap.put("deliveryAddress",order_address);
////                            orderMap.put("deliveryDate",order_date);
////                            orderMap.put("deliveryTime",order_time);
////                            orderMap.put("deliveryPaymentMethod",order_payment);
//
//                            cartlistRef.child(timestamp).child("Items").child(clthname).setValue(itemMap);
//                            Toast.makeText(ConfirmActivity.this, "successfull", Toast.LENGTH_SHORT).show();
//                        }
//                        Intent intent = new Intent(ConfirmActivity.this, BtmNavigationActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(ConfirmActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

//                Intent intent = new Intent(ConfirmActivity.this, BtmNavigationActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

        populateRecyclerview();

    }

    private void populateRecyclerview() {
        cartPojos = new ArrayList<>();
        try {
            cartPojos = getFAlldata();
            finalItemListAdapter = new FinalItemListAdapter(ConfirmActivity.this,cartPojos);
            recyclerView.setAdapter(finalItemListAdapter);
            finalItemListAdapter.notifyDataSetChanged();
        }catch (Exception e)
        {
            Toast.makeText(ConfirmActivity.this, "error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    public ArrayList<CartPojo> getFAlldata()
    {
        ArrayList<CartPojo> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = washFoldDatabaseHelper.getReadableDatabase();
        String query ="SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        while (cursor.moveToNext())
        {
            String clthname = cursor.getString(1);
            String clth_per_piece_price = cursor.getString(2);
            String clth_total_price = cursor.getString(3);
            String clth_quantity = cursor.getString(4);
            String clth_catg_name = cursor.getString(5);

            CartPojo dataModel = new CartPojo(clthname, clth_per_piece_price, clth_quantity,clth_catg_name, clth_total_price);
            arrayList.add(dataModel);
        }
        sqLiteDatabase.close();
        return arrayList;
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




