//package com.example.laundryapp.FRAGMENT;
//
//import androidx.fragment.app.Fragment;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.example.laundryapp.R;
//
//public class ConfirmFragment extends Fragment implements View.OnClickListener{
//
//    private OnStepFourListener mListener;
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    private String mParam1;
//    private String mParam2;
//
//    public ConfirmFragment(){
//
//    }
//
//    public static ConfirmFragment newInstance(String param1, String param2) {
//        ConfirmFragment fragment = new ConfirmFragment();
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
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_confirm,container, false);
//        return view;
//    }
//
//    private RelativeLayout nextBT;
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        nextBT=view.findViewById(R.id.btn_finish);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        nextBT.setOnClickListener(this);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        nextBT.setOnClickListener(null);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof ConfirmFragment.OnStepFourListener) {
//            mListener = (ConfirmFragment.OnStepFourListener) context;
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
//        nextBT=null;
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//        case R.id.btn_finish:
//        if (mListener != null)
//            mListener.onNextPressed(this);
//        break;
//    }
//    }
//
//    public interface OnStepFourListener {
//        void onBackPressed(Fragment fragment);
//        void onNextPressed(Fragment fragment);
//    }
//}
