//package com.example.laundryapp.UI;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.example.laundryapp.ADAPTER.NonSwipableViewPager;
//import com.example.laundryapp.FRAGMENT.ConfirmFragment;
//import com.example.laundryapp.FRAGMENT.DateTimeFragment;
//import com.example.laundryapp.FRAGMENT.LocationFragment;
//import com.example.laundryapp.FRAGMENT.PaymentFragment;
//import com.example.laundryapp.R;
//import com.rakshakhegde.stepperindicator.StepperIndicator;
//
//public class SchedulePickupActivity extends AppCompatActivity implements
//        LocationFragment.OnStepOneListener,
//        DateTimeFragment.OnStepTwoListener,
//        PaymentFragment.OnStepThreeListener,
//        ConfirmFragment.OnStepFourListener
//        {
//
//    private StepperIndicator stepView;
//    private NonSwipableViewPager nonSwipableViewPager;
//    private FPagerAdater fPagerAdater;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//       setContentView(R.layout.activity_schedule_pickup);
//
//        fPagerAdater = new FPagerAdater(getSupportFragmentManager());
//        nonSwipableViewPager = findViewById(R.id.viewpager_nonswipable);
//        nonSwipableViewPager.setAdapter(fPagerAdater);
//
//        nonSwipableViewPager.setPagingEnabled(false);
//
//        stepView = findViewById(R.id.step_view);
//        stepView.setViewPager(nonSwipableViewPager);
//        // or keep last page as "end page"
//        stepView.setViewPager(nonSwipableViewPager, nonSwipableViewPager.getAdapter().getCount() - 1);
//    }
//
//    private class FPagerAdater extends FragmentPagerAdapter {
//        public FPagerAdater(@NonNull FragmentManager fm) {
//            super(fm);
//        }
//        @NonNull
//        @Override
//        public androidx.fragment.app.Fragment getItem(int position) {
//            switch(position){
//                case 0:
//                    return LocationFragment.newInstance("","");
//                case 1:
//                    return DateTimeFragment.newInstance("","");
//                case 2:
//                    return PaymentFragment.newInstance("","");
//                case 3:
//                    return ConfirmFragment.newInstance("","");
//            }
//            return null;
//        }
//
//        @Override
//        public int getCount() {
//            return 4;
//        }
//    }
//            @Override
//            public void onBackPressed(androidx.fragment.app.Fragment fragment) {
//                if (fragment instanceof DateTimeFragment) {
//                    nonSwipableViewPager.setCurrentItem(0);
//                } else if (fragment instanceof PaymentFragment) {
//                    nonSwipableViewPager.setCurrentItem(1);
//                } else if (fragment instanceof ConfirmFragment) {
//                    nonSwipableViewPager.setCurrentItem(2);
//                }
//            }
//
//            @Override
//            public void onNextPressed(Fragment fragment) {
//                if (fragment instanceof LocationFragment) {
//                    nonSwipableViewPager.setCurrentItem(1);
//                } else if (fragment instanceof DateTimeFragment) {
//                    nonSwipableViewPager.setCurrentItem(2);
//                }else if (fragment instanceof PaymentFragment){
//                    nonSwipableViewPager.setCurrentItem(3);
//                }
//                else if (fragment instanceof ConfirmFragment) {
//                    Toast.makeText(this, "Your Booking confirmed!!", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }
//}