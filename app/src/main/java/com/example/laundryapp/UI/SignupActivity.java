package com.example.laundryapp.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundryapp.FRAGMENT.LoadingFragment;
import com.example.laundryapp.POJO.SignupPojo;
import com.example.laundryapp.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
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
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private RelativeLayout signup_btn;
    private TextView signin_txtvw;
    private RelativeLayout google_signup, facebook_signup;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference databaseReference;
    private TextInputEditText nameedtxt,emailedtxt,paswrdedtxt;
    private CheckBox checkBox;

    private CallbackManager mCallbackManager;

    private GoogleSignInClient googleSignInClient;
    private final static int GOOGLE_SIGN_IN = 10001;
    private final static int FACEBOOK_SIGN_IN = 101;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])"+ //at least 1 digit
            "(?=.*[a-z])"+ //at least 1 lower case letter
            "(?=.*[@#$%^&*-=+])" + //at least 1 special character
            "(?=\\S+$)"+ //no white spaces
            ".{6,}"+// minimim character
            "$");

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthStateListener);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (firebaseAuth.getCurrentUser() !=null)
//                {
//                    startActivity(new Intent(SignupActivity.this, PhonenumActivity.class));
//                }
//            }
//        };

        signup_btn = findViewById(R.id.btn_signup);
        signin_txtvw = findViewById(R.id.txtvw_signin);
        google_signup = findViewById(R.id.btn_google_signup);
        facebook_signup = findViewById(R.id.btn_facebook_signup);
        nameedtxt = findViewById(R.id.edtxt_signup_name);
        emailedtxt = findViewById(R.id.edtxt_signup_email);
        paswrdedtxt = findViewById(R.id.edtxt_signup_password);
        checkBox = findViewById(R.id.checkbox_signup);

        mAuth = FirebaseAuth.getInstance();

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameedtxt.getText().toString().trim();
                String email = emailedtxt.getText().toString().trim();
                String paswrd = paswrdedtxt.getText().toString().trim();
                if (!name.isEmpty()){
                    if (!email.isEmpty()){
                        if (!paswrd.isEmpty()){
                           if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                               if (PASSWORD_PATTERN.matcher(paswrd).matches()){
                                   if (checkBox.isChecked()){
                                       showProgressDialog(); 
                                       mAuth.createUserWithEmailAndPassword(email,paswrd)
                                               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<AuthResult> task) {
                                               if (task.isSuccessful()){
                                                   SignupPojo signupPojo = new SignupPojo(name,email,paswrd);
                                                   FirebaseDatabase.getInstance().getReference("userdata")
                                                           .child(FirebaseAuth
                                                           .getInstance()
                                                           .getCurrentUser()
                                                           .getUid())
                                                           .setValue(signupPojo)
                                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                       @Override
                                                       public void onComplete(@NonNull Task<Void> task) {
                                                           if (task.isSuccessful()){
                                                               dismissProgressDialog();
                                                               Intent intent = new Intent(SignupActivity.this, PhonenumActivity.class);
                                                               startActivity(intent);
                                                               Toast.makeText(SignupActivity.this, "Signup Successfully!!", Toast.LENGTH_SHORT).show();
                                                           }else {
                                                               Toast.makeText(SignupActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                                                           }
                                                       }
                                                   });
                                               }else {
                                                   Toast.makeText(SignupActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       });
                                   }else {
                                       Toast.makeText(SignupActivity.this, "Please agree our terms & conditions", Toast.LENGTH_SHORT).show();
                                   }
                               }else {
                                   Toast.makeText(SignupActivity.this, "Password is weak", Toast.LENGTH_SHORT).show();
                               }
                           }else {
                               Toast.makeText(SignupActivity.this, "Enter valid email id", Toast.LENGTH_SHORT).show();
                           }
                        }else {
                            paswrdedtxt.setError("Please enter your password");
                        }
                    }else {
                        emailedtxt.setError("Please enter your email id");
                    }
                }else{
                    nameedtxt.setError("Please enter your name");
                }
            }
        });

        signin_txtvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //google sign in
        google_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                switch (v.getId()) {
                    case R.id.btn_google_signup:
                        mSignup();
                        break;
                }

            }
        });
        createRequest();

        //facebook signin
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        facebook_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(SignupActivity.this, Arrays.asList("email","public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("TAG", "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d("TAG", "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("TAG", "facebook:onError", error);
                        // ...
                    }
                });
            }
        });
    }

    private void createRequest(){
        dismissProgressDialog();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("584788650962-k3obkslsn11sa0sa1vq2c4nf3rbenr0e.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    private void mSignup(){
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent,GOOGLE_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showProgressDialog();
        if (requestCode == GOOGLE_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            }catch (Exception e){
                Toast.makeText(SignupActivity.this, "Google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }else{
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    gotoProfile(user);
                }else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(SignupActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            gotoProfile(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignupActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void gotoProfile(FirebaseUser user){
        dismissProgressDialog();
        Intent intent=new Intent(SignupActivity.this,PhonenumActivity.class);
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