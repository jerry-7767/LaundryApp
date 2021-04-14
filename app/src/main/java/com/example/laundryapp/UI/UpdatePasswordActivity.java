package com.example.laundryapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.laundryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdatePasswordActivity extends AppCompatActivity {

    private TextInputEditText oldpswrd_edttxt,newpswrd_edttxt,cnfrmpswrd_edttxt;
    private RelativeLayout btn_resetpswrd;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        oldpswrd_edttxt = findViewById(R.id.edtxt_enter_old_password);
        newpswrd_edttxt = findViewById(R.id.edtxt_update_enter_new_password);
        cnfrmpswrd_edttxt = findViewById(R.id.edtxt_update_confirm_password);
        btn_resetpswrd = findViewById(R.id.btn_reset_pswrd);

        btn_resetpswrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpswrd = oldpswrd_edttxt.getText().toString();
                String newpswrd = newpswrd_edttxt.getText().toString();
                String cnfirmpswrd = cnfrmpswrd_edttxt.getText().toString();

                if (oldpswrd.isEmpty() || newpswrd.isEmpty() || cnfirmpswrd.isEmpty()){
                    Toast.makeText(UpdatePasswordActivity.this, "All field are required!", Toast.LENGTH_SHORT).show();
                }else if (newpswrd.length()<6){
                    Toast.makeText(UpdatePasswordActivity.this, "Weak Password", Toast.LENGTH_SHORT).show();
                }else if (!cnfirmpswrd.equals(newpswrd)){
                    Toast.makeText(UpdatePasswordActivity.this, "confirm password does not match with new password", Toast.LENGTH_SHORT).show();
                }else {
                    changePswrd(oldpswrd,newpswrd);
                }
            }
        });
    }
    private void changePswrd(String oldpsw,String newpsw){
        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(),oldpsw);
        user.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    user.updatePassword(newpsw).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mAuth.signOut();
                                Intent intent = new Intent(UpdatePasswordActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(UpdatePasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}