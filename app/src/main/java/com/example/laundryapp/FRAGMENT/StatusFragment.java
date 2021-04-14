package com.example.laundryapp.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.laundryapp.R;

import java.util.ArrayDeque;
import java.util.Deque;

public class StatusFragment extends Fragment implements View.OnClickListener{

    private View view;
    private Deque<Integer> deque = new ArrayDeque<>(4);
    private RadioButton ongoingorder_btn;
    private RadioButton pastorder_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_status,container,false);

        ongoingorder_btn = view.findViewById(R.id.radio_ongoingorder);
        pastorder_btn = view.findViewById(R.id.radio_pastorders);
        ongoingorder_btn.setOnClickListener(this);
        pastorder_btn.setOnClickListener(this);

        deque.push(R.id.radio_ongoingorder);
        replaceOrderFragment(new OngoingOrderFragment());
        ongoingorder_btn.setChecked(true);

        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.radio_ongoingorder:
                fragment = new OngoingOrderFragment();
                replaceOrderFragment(fragment);
                break;

            case R.id.radio_pastorders:
                fragment = new PastOrdersFragment();
                replaceOrderFragment(fragment);
                break;
        }
    }

    public void replaceOrderFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_order_status,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

//    private Fragment getFragment(int itemId){
//        switch (itemId){
//            case R.id.radio_ongoingorder:
//                ongoingorder_btn.setChecked(true);
//                return new OngoingOrderFragment();
//            case R.id.radio_pastorders:
//                pastorder_btn.setChecked(true);
//                return new PastOrdersFragment();
//        }
//        ongoingorder_btn.setChecked(true);
//        return new OngoingOrderFragment();
//    }

}
