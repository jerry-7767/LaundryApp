//package com.example.laundryapp.FRAGMENT;
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//
//import com.example.laundryapp.R;
//
//import android.location.LocationListener;
//
//import com.example.laundryapp.UI.AddressActivity;
//import com.google.android.material.button.MaterialButton;
//
//import java.util.List;
//import java.util.Locale;
//
//public class LocationFragment extends Fragment implements View.OnClickListener,LocationListener{
//
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    private String mParam1;
//    private String mParam2;
//    private OnStepOneListener mListener;
////    private TextView area_name, full_address;
////    private MaterialButton materialButton;
////    private LocationManager locationManager;
////    private ProgressBar area_progressbar,address_progressbar;
////    public static String final_address;
////    int theme = Color.parseColor("#ff0cf6fd");
////    int gray = Color.parseColor("#a5a5a5");
//
//    public LocationFragment() {
//    }
//
//    public static LocationFragment newInstance(String param1, String param2) {
//        LocationFragment fragment = new LocationFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(ARG_PARAM1, param1);
//        bundle.putString(ARG_PARAM2, param2);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
////     Button button;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_location, container, false);
//
////        grantPermission();
////        checkLocationisEnableorNot();
////        getCurrentLocation();
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
////        button = view.findViewById(R.id.btn_confirm_location);
////        area_name = view.findViewById(R.id.txtvw_area_city_name);
////        full_address = view.findViewById(R.id.txtvw_full_address);
////        materialButton = view.findViewById(R.id.btn_choose_address);
////        area_progressbar = view.findViewById(R.id.progress_bar_area);
////        address_progressbar = view.findViewById(R.id.progress_bar_address);
////
////        button.setEnabled(false);
////        button.setBackgroundColor(gray);
////
////        materialButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(getActivity(), AddressActivity.class);
////                startActivity(intent);
////            }
////        });
//    }
//
////    private void checkLocationisEnableorNot() {
////        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
////        boolean gpsEnable = false;
////        boolean networkEnable = false;
////
////        try {
////            gpsEnable = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        try {
////            networkEnable = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        if (!gpsEnable && !networkEnable) {
////            new AlertDialog.Builder(getActivity()).setTitle("Enable GPS Service")
////                    .setCancelable(false)
////                    .setPositiveButton("Enable", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
////                        }
////                    }).setNegativeButton("Cancel", null).show();
////        }
////    }
////
////    private void grantPermission() {
////        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
////                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
////                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
////                    Manifest.permission.ACCESS_COARSE_LOCATION
////            }, 100);
////        }
////    }
////
////    private void getCurrentLocation() {
////        try {
////            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
////            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,500,5, (LocationListener)this);
////        }catch (SecurityException e){
////            e.printStackTrace();
////        }
////    }
////
////    @Override
////    public void onLocationChanged(Location location) {
////        try {
////            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
////            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
////            address_progressbar.setVisibility(View.INVISIBLE);
////            area_progressbar.setVisibility(View.INVISIBLE);
////            area_name.setVisibility(View.VISIBLE);
////            full_address.setVisibility(View.VISIBLE);
////            button.setEnabled(true);
////            button.setBackgroundColor(theme);
////            area_name.setText(addresses.get(0).getSubLocality());
////            full_address.setText(addresses.get(0).getAddressLine(0));
////            final_address = full_address.getText().toString();
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////    }
////
////    @Override
////    public void onProviderEnabled(@NonNull String provider) {
////
////    }
////
////    @Override
////    public void onProviderDisabled(@NonNull String provider) {
////
////    }
////
////    @Override
////    public void onStatusChanged(String provider, int status, Bundle extras) {
////
////    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        button.setOnClickListener(null);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        button.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_confirm_location:
//                if (mListener!=null)
//                mListener.onNextPressed(this);
//                break;
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof LocationFragment.OnStepOneListener) {
//            mListener = (LocationFragment.OnStepOneListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener=null;
//        button = null;
//    }
//
//    public interface OnStepOneListener {
//        void onBackPressed(Fragment fragment);
//        void onNextPressed(Fragment fragment);
//    }
//}