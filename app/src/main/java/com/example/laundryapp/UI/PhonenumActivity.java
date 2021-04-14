package com.example.laundryapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class PhonenumActivity extends AppCompatActivity {

    private TextInputEditText phone_edtxt;
    private RelativeLayout btn_sendotp;
    private FirebaseAuth mAuth;
    private TextView phonenum_code;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenum);

        mAuth = FirebaseAuth.getInstance();

        phone_edtxt = findViewById(R.id.edtxt_enter_phone_num);
        btn_sendotp = findViewById(R.id.btn_send_otp);
        phonenum_code = findViewById(R.id.txtvw_phone_code);

        btn_sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getusernumber = phone_edtxt.getText().toString().trim();
                String phone_code = phonenum_code.getText().toString();
                String full_number = phone_code+getusernumber;

                if (getusernumber.isEmpty() || full_number.length()<10)
                {
                    Toast.makeText(PhonenumActivity.this, "Enter Phone number!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String phoneNumber = full_number;
                Intent intent = new Intent(PhonenumActivity.this, OTPActivity.class);
                intent.putExtra("phonenum",phoneNumber);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}










