package com.example.laundryapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.laundryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {

    private PinView pinView;
    private TextView phonenum_txtvw,txtvw_count,resend_otp;
    private RelativeLayout verify_btn;
    public String codebySystem;
    private FirebaseAuth mAuth;
    private CountDownTimer mCountDownTimer;
    private static final long START_TIME_IN_MILLIS = 60000;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        mAuth = FirebaseAuth.getInstance();

        pinView = findViewById(R.id.pinvw_otp);
        verify_btn = findViewById(R.id.btn_verify);
        phonenum_txtvw = findViewById(R.id.txtvw_phone_num);
        txtvw_count = findViewById(R.id.counttime);
        resend_otp = findViewById(R.id.txtvw_resent_otp);

        String getnumber = getIntent().getStringExtra("phonenum");
        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationUser(getnumber);
                Toast.makeText(OTPActivity.this, "OTP Resent Successfully!!", Toast.LENGTH_SHORT).show();
            }
        });
        phonenum_txtvw.setText(getnumber);
        verificationUser(getnumber);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNextActivity();
            }
        });

        startTimer();

    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codebySystem,code);
        signinUsingCredential(credential);
    }

    private void signinUsingCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(OTPActivity.this, "Verification successfull", Toast.LENGTH_SHORT).show();
                }else {
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                        Toast.makeText(OTPActivity.this, "Failed!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void verificationUser(String getnumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(getnumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallback)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback
            = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codebySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code!=null){
                pinView.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
    
    private void callNextActivity(){
        String code = pinView.getText().toString();
        if (!code.isEmpty()){
            verifyCode(code);
            Intent intent = new Intent(OTPActivity.this,WalkthroughIntroActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                txtvw_count.setVisibility(View.INVISIBLE);
                resend_otp.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        txtvw_count.setText(timeLeftFormatted);
    }
}









