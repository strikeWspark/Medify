package com.dryfire.medify.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dryfire.medify.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextInputLayout usernameInput,emailInput,passwordInput;
    private TextInputEditText usernameEdit,emailEdit,passwordEdit;
    private MaterialButton signUpButton,googleSignupButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        new SplashScreen().statusBarView(getWindow());



        mAuth = FirebaseAuth.getInstance();

        toolbar = (Toolbar) findViewById(R.id.signup_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usernameInput = (TextInputLayout) findViewById(R.id.signup_input_username);
        usernameEdit = (TextInputEditText) findViewById(R.id.signup_edit_username);

        emailInput = (TextInputLayout) findViewById(R.id.signup_input_email);
        emailEdit = (TextInputEditText) findViewById(R.id.signup_edit_email);

        passwordInput = (TextInputLayout) findViewById(R.id.signup_input_password);
        passwordEdit = (TextInputEditText) findViewById(R.id.signup_edit_password);

        signUpButton = (MaterialButton) findViewById(R.id.signupButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();
                if(new LoginActivity().isPassword(password)){
                    signup(email,password);
                }else{
                    Toast.makeText(SignUpActivity.this, "Passowrd Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                finish();
            }
        });
    }

    private void signup(String email,String password) {

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                        finish();
                    }
                });

    }
}
