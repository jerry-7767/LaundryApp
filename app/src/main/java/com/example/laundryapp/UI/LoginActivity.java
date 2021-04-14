package com.example.laundryapp.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundryapp.FRAGMENT.LoadingFragment;
import com.example.laundryapp.POJO.SignupPojo;
import com.example.laundryapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextView signuptxtvw, frgtpswrdtxtvw;
    private RelativeLayout signinbtn, googlesigninbtn, facebooksigninbtn;
    private TextInputEditText emailedtxt, paswrdedtxt;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private CheckBox checkBox;
    private GoogleSignInClient googleSignInClient;
    private final static int RC_SIGN_IN = 10001;
    private DatabaseReference mReference;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])"+ //at least 1 digit
            "(?=.*[a-z])"+ //at least 1 lower case letter
            "(?=.*[@#$%^&*-=+])" + //at least 1 special character
            "(?=\\S+$)"+ //no white spaces
            ".{6,}"+// minimim character
            "$");

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() !=null)
                {
                    startActivity(new Intent(LoginActivity.this, BtmNavigationActivity.class));
                }
            }
        };

        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference("userdata");
        checkBox = findViewById(R.id.checkboxid);
        signinbtn = findViewById(R.id.btn_signin);
        googlesigninbtn = findViewById(R.id.btn_google_signin);
        facebooksigninbtn = findViewById(R.id.btn_facebook_signin);
        frgtpswrdtxtvw = findViewById(R.id.txtvw_frgt_paswrd);
        signuptxtvw = findViewById(R.id.txtvw_signup);
        emailedtxt = findViewById(R.id.edtxt_email);
        paswrdedtxt = findViewById(R.id.edtxt_password);

        frgtpswrdtxtvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPswrdActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signuptxtvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailedtxt.getText().toString().trim();
                String pswrd = paswrdedtxt.getText().toString().trim();
                if (!email.isEmpty()){
                    if (!pswrd.isEmpty()){
                        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                            if (PASSWORD_PATTERN.matcher(pswrd).matches()){
                                if (checkBox.isChecked()){
                                    showProgressDialog();
                                    mAuth.signInWithEmailAndPassword(email,pswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                dismissProgressDialog();
                                                Intent intent = new Intent(LoginActivity.this, BtmNavigationActivity.class);
                                                startActivity(intent);
                                            }else {
                                            Toast.makeText(LoginActivity.this, "don't have an account", Toast.LENGTH_SHORT).show();
                                                dismissProgressDialog();
                                        }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        });

        //google sign in
        googlesigninbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                switch (v.getId()) {
                    case R.id.btn_google_signin:
                        mSignin();
                        break;
                }

            }
        });
        createRequest();
    }

    private void createRequest(){
        dismissProgressDialog();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("584788650962-k3obkslsn11sa0sa1vq2c4nf3rbenr0e.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    private void mSignin(){
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showProgressDialog();
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            }catch (Exception e){
                Toast.makeText(LoginActivity.this, "Google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    gotoProfile();
                }else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void gotoProfile(){
        dismissProgressDialog();
        Intent intent=new Intent(LoginActivity.this,PhonenumActivity.class);
        startActivity(intent);
        finish();
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