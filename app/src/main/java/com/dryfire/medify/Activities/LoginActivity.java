package com.dryfire.medify.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private FirebaseAuth mAuth;
    private FirebaseUser mUSer;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signingIn();

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
                            Toast.makeText(LoginActivity.this,"SignEd IN",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "Not Signed In", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private boolean isPassowrd(Editable pwd){
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
