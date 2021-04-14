//package com.example.laundryapp.FRAGMENT;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import com.example.laundryapp.R;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//import devs.mulham.horizontalcalendar.HorizontalCalendar;
//import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
//
//public class DateTimeFragment extends Fragment implements View.OnClickListener {
//
//    private OnStepTwoListener mListener;
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    private String mParam1;
//    private String mParam2;
//    public static String final_date;
//
//    public DateTimeFragment(){
//    }
//
//    public static DateTimeFragment newInstance(String param1, String param2) {
//        DateTimeFragment fragment = new DateTimeFragment();
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
//        View view = inflater.inflate(R.layout.fragment_datetime,container, false);
//        return view;
//    }
//
//    private RelativeLayout nextBT;
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        nextBT=view.findViewById(R.id.btn_1nxt);
//
//        /* starts before 1 month from now */
//        Calendar startDate = Calendar.getInstance();
//        startDate.add(Calendar.DAY_OF_WEEK, 0);
//
//        /* ends after 1 month from now */
//        Calendar endDate = Calendar.getInstance();
//        endDate.add(Calendar.MONTH, 1);
//
//
//        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
//                .range(startDate, endDate)
//                .datesNumberOnScreen(5)
//                .build();
//
//        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
//            @Override
//            public void onDateSelected(Calendar date, int position) {
//                Calendar cal = horizontalCalendar.getDateAt(position);
//
//                Calendar calendar = horizontalCalendar.getSelectedDate();
//
//                calendar.add(Calendar.DATE,0);
//                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
//
//                final_date = format1.format(calendar.getTime());
//                Toast.makeText(getActivity(), "Selected Date:-"+final_date , Toast.LENGTH_SHORT).show();
//            }
//        });
//
////        radioGroup_minute.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
////            @Override
////            public void onCheckedChanged(RadioGroup group, int checkedId) {
////                int id = group.getCheckedRadioButtonId();
////                radioButton = (RadioButton) group.findViewById(id);
////                 minute = radioButton.getText().toString();
////                Toast.makeText(getActivity(), radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
////            }
////        });
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
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_1nxt:
//                if (mListener != null)
//                    mListener.onNextPressed(this);
//                break;
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnStepTwoListener) {
//            mListener = (OnStepTwoListener) context;
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
//    public interface OnStepTwoListener {
//        void onBackPressed(Fragment fragment);
//        void onNextPressed(Fragment fragment);
//    }
//}
