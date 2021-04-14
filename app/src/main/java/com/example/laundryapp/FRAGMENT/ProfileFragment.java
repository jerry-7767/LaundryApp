package com.example.laundryapp.FRAGMENT;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.laundryapp.POJO.SelectClothsPojo;
import com.example.laundryapp.POJO.SignupPojo;
import com.example.laundryapp.R;
import com.example.laundryapp.UI.AddressActivity;
import com.example.laundryapp.UI.LoginActivity;
import com.example.laundryapp.UI.NotificationActivity;
import com.example.laundryapp.UI.RefferEarnActivity;
import com.example.laundryapp.UI.UpdatePasswordActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private View view;
    private RelativeLayout number_layout, address_layout, payments_layout,
            notification_layout,refferearn_layout,share_layout,terms_layout,signout_layout;
    private TextView usernametxt,useremailtxt,userphonenumber_txtvw;
    private FirebaseUser firebaseUser;
    private String userid;
    private DatabaseReference databaseReference;
    private Dialog builder;
    private RelativeLayout nobtn, yesbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile,container, false);

        builder = new Dialog(getActivity());

        number_layout = view.findViewById(R.id.layout_phonenumber);
        address_layout = view.findViewById(R.id.layout_address);
        payments_layout = view.findViewById(R.id.layout_payment);
        notification_layout = view.findViewById(R.id.layout_notification);
        refferearn_layout = view.findViewById(R.id.layout_refferandearn);
        share_layout = view.findViewById(R.id.layout_share);
        terms_layout = view.findViewById(R.id.layout_terms);
        signout_layout = view.findViewById(R.id.layout_signout);
        userphonenumber_txtvw = view.findViewById(R.id.txtvw_show_user_number);

        usernametxt = view.findViewById(R.id.txtvw_user_name);
        useremailtxt = view.findViewById(R.id.txtvw_user_email);

       firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
       String ph = firebaseUser.getPhoneNumber();
       userphonenumber_txtvw.setText(ph);

        number_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), ph, Toast.LENGTH_SHORT).show();
            }
        });

        address_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), AddressActivity.class);
               startActivity(intent);
            }
        });

        payments_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "payment", Toast.LENGTH_SHORT).show();
            }
        });

        notification_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        refferearn_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RefferEarnActivity.class);
                startActivity(intent);
            }
        });

        share_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "share", Toast.LENGTH_SHORT).show();
            }
        });

        terms_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "terms", Toast.LENGTH_SHORT).show();
            }
        });

        signout_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showSignoutDialog();
            }
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("userdata");
        userid = firebaseUser.getUid();

        databaseReference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SignupPojo signupPojo = snapshot.getValue(SignupPojo.class);
                if (signupPojo!=null){
                    String showname = signupPojo.name;
                    String showemail = signupPojo.email;

                    usernametxt.setText(showname);
                    useremailtxt.setText(showemail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //set google signin user image and emailid
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (googleSignInAccount != null) {
            usernametxt.setText(googleSignInAccount.getDisplayName());
            useremailtxt.setText(googleSignInAccount.getEmail());
            String personImage = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                personImage = Objects.requireNonNull(googleSignInAccount.getPhotoUrl()).toString();
            }
            ImageView userImage = view.findViewById(R.id.circle_img_user);
            Glide.with(this).load(personImage).into(userImage);
        }
        return view;
    }
    private void finish(){
        finish();
    }

    private void showSignoutDialog(){
    builder.setContentView(R.layout.single_signout_dialog_layout);
    nobtn = builder.findViewById(R.id.btn_no);
    yesbtn = builder.findViewById(R.id.btn_yes);
        builder.setCancelable(false);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        builder.show();
        yesbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    });

        nobtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            builder.dismiss();
        }
    });
    }
}
