//package com.example.laundryapp.FRAGMENT;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.laundryapp.ADAPTER.PaymentAdapter;
//import com.example.laundryapp.DATA.PaymentOptionsData;
//import com.example.laundryapp.POJO.CartPojo;
//import com.example.laundryapp.POJO.PaymentPojo;
//import com.example.laundryapp.R;
//import com.google.firebase.auth.FirebaseAuth;
//import java.util.ArrayList;
//
//public class PaymentFragment extends Fragment implements View.OnClickListener{
//
//    private OnStepThreeListener mListener;
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    private String mParam1;
//    private String mParam2;
//    private FirebaseAuth mAuth;
//    private ArrayList<CartPojo> cartPojos;
//
//    public PaymentFragment(){
//
//    }
//
//    public static PaymentFragment newInstance(String param1, String param2) {
//        PaymentFragment fragment = new PaymentFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    private RecyclerView payment_recyclerview;
//    private ArrayList<PaymentPojo> paymentPojos;
//    public PaymentAdapter paymentAdapter;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        mAuth = FirebaseAuth.getInstance();
//        View view = inflater.inflate(R.layout.fragment_payment,container, false);
//        payment_recyclerview = view.findViewById(R.id.recyclervw_payment_options);
//        paymentPojos = new ArrayList<>();
//        for (int i=0; i< PaymentOptionsData.paymentimages.length; i++){
//            paymentPojos.add(new PaymentPojo(
//                    PaymentOptionsData.paymenttype[i],
//                    PaymentOptionsData.paymentimages[i]
//                        ));
//        }
//        payment_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        paymentAdapter = new PaymentAdapter(getContext(),paymentPojos);
//        payment_recyclerview.setAdapter(paymentAdapter);
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        confirmbtn = view.findViewById(R.id.btn_confirm_order);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        confirmbtn.setOnClickListener(this);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        confirmbtn.setOnClickListener(null);
//    }
//
//    private RelativeLayout confirmbtn;
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_confirm_order:
//                if (mListener != null || PaymentAdapter.radioButton.isChecked())
//                    mListener.onNextPressed(this);
//                break;
//        }
////        //showProgressDialog();
////        String saveCurrentTime, saveCurrentDate;
////        Calendar calendar = Calendar.getInstance();
////        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
////        saveCurrentDate = currentDate.format(calendar.getTime());
////
////        timestamp = ""+System.currentTimeMillis();
////
////        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
////        saveCurrentTime = currentTime.format(calendar.getTime());
////
////        String final_cost = final_grandtotal;
////
////        String order_address = final_address;
////        String order_date = final_date;
////        String order_time = final_time;
////        String order_payment = final_payment;
//////                .getText().toString().trim().replace("$","");
////        final HashMap<String, Object> cartMap = new HashMap<>();
////        cartMap.put("orderBy",""+mAuth.getUid());
////        cartMap.put("orderId",""+timestamp);
////        cartMap.put("orderTime",""+saveCurrentTime);
////        cartMap.put("orderDate",saveCurrentDate);
////        cartMap.put("orderStatus","In Progress");
////        cartMap.put("orderCost",""+final_cost);
////
////        cartMap.put("orderaddress",order_address);
////        cartMap.put("orderdate",order_date);
////        cartMap.put("ordertime",order_time);
////        cartMap.put("orderpayment",order_payment);
////
////        final DatabaseReference cartlistRef = FirebaseDatabase.getInstance().getReference("User").child("Orders");
////        cartlistRef.child(timestamp).child("Items").setValue(cartMap)
////                .addOnCompleteListener(new OnCompleteListener<Void>() {
////            @Override
////            public void onComplete(@NonNull Task<Void> task) {
////                Toast.makeText(getContext(), "successfull!!", Toast.LENGTH_SHORT).show();
////            }
////        }).addOnFailureListener(new OnFailureListener() {
////            @Override
////            public void onFailure(@NonNull Exception e) {
////                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
////            }
////        });
////                .addOnSuccessListener(new OnSuccessListener<Void>() {
////                    @Override
////                    public void onSuccess(Void aVoid) {
////                        Toast.makeText(getContext(), "successfull!!", Toast.LENGTH_SHORT).show();
////                    }
////                }).addOnFailureListener(new OnFailureListener() {
////            @Override
////            public void onFailure(@NonNull Exception e) {
////                //dismissProgressDialog();
////                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
////            }
////        });
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof PaymentFragment.OnStepThreeListener) {
//            mListener = (PaymentFragment.OnStepThreeListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//        confirmbtn=null;
//    }
//
//    public interface OnStepThreeListener {
//        void onBackPressed(Fragment fragment);
//        void onNextPressed(Fragment fragment);
//    }
//    private void showProgressDialog(){
//        LoadingFragment loadingFragment = new LoadingFragment();
//        loadingFragment.newInstance().show(getActivity().getSupportFragmentManager(),"");
//    }
//
//    private void dismissProgressDialog(){
//        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("fragment_dialog");
//        if (prev != null) {
//            LoadingFragment df = (LoadingFragment) prev;
//            df.dismiss();
//        }
//    }
//}
