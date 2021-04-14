package com.example.laundryapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundryapp.FRAGMENT.LoadingFragment;
import com.example.laundryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class ForgetPswrdActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText edt_txt_input_email_reset_paswrd;
    private RelativeLayout btn_send_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pswrd);

        mAuth = FirebaseAuth.getInstance();

        edt_txt_input_email_reset_paswrd = findViewById(R.id.edtxt_enter_email_reset_pswrd);
        btn_send_email = findViewById(R.id.btn_send_email_reset_pswrd);
        btn_send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                mAuth.fetchSignInMethodsForEmail(edt_txt_input_email_reset_paswrd.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        dismissProgressDialog();
                        if (task.getResult().getSignInMethods().isEmpty()){
                            Toast.makeText(ForgetPswrdActivity.this, "This is not an registered email, you can create new account.", Toast.LENGTH_SHORT).show();
                        }else {
                            mAuth.sendPasswordResetEmail(edt_txt_input_email_reset_paswrd.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(ForgetPswrdActivity.this, "An email to reset your password has been sent to your email address", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(ForgetPswrdActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
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