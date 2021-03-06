package com.dryfire.medify.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dryfire.medify.R;
import com.dryfire.medify.Util.SharedPrefs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    SharedPrefs sharedPrefs;
    private TextInputLayout usernameInput,passwordInput;
    private TextInputEditText usernameEdit,passowrdEdit;
    private MaterialButton loginButton;
    private TextView signUpTextButton;
    private FirebaseAuth mAuth;
    private FirebaseUser mUSer;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        sharedPrefs = new SharedPrefs(this);
        if(sharedPrefs.loadNightModeState() == true){
            setTheme(R.style.darktheme);
        }else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new SplashScreen().statusBarView(getWindow());

        builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.progress_dialog_layout,null);
        builder.setView(view);

        dialog = builder.create();


        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                mUSer = firebaseAuth.getCurrentUser();
                if(mUSer != null){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }
            }
        };
        usernameInput = (TextInputLayout) findViewById(R.id.medify_input_username);
        usernameEdit = (TextInputEditText) findViewById(R.id.medify_edit_username);

        passwordInput = (TextInputLayout) findViewById(R.id.medify_textinput_password);
        passowrdEdit = (TextInputEditText) findViewById(R.id.medify_edittext_password);
        loginButton = (MaterialButton) findViewById(R.id.signin_button);

        signUpTextButton = (TextView) findViewById(R.id.signup_textButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
                signingIn();

            }
        });

        signUpTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
    }

    private void signingIn() {

        String email = usernameEdit.getText().toString().trim();
        String pwd = passowrdEdit.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                            dialog.dismiss();
                            Toast.makeText(LoginActivity.this,"SignEd IN",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "Not Signed In", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public boolean isPassword(String pwd){
        return pwd != null && pwd.length() >= 8;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthStateListener);
    }
}
