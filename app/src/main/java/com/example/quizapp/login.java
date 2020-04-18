package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email, password;
    Button login;
    TextView forget_pass, create;
    ProgressBar timeToLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        create = (TextView) findViewById(R.id.creat);
        fAuth = FirebaseAuth.getInstance();
        timeToLogin = (ProgressBar) findViewById(R.id.timeReq);
        forget_pass = (TextView) findViewById(R.id.forgetpass);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(login.this, register.class));
            finish();
        }


        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, forget_password.class));

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }

            private void login() {
                timeToLogin.setVisibility(View.VISIBLE);

                String emailf = email.getText().toString();
                String passwordf = password.getText().toString();

                if(emailf.isEmpty())
                {
                    email.setError("Email is Required");
                    return;
                }
                if(passwordf.isEmpty())
                {
                    password.setError("Password is Required");
                    return;
                }
                if(passwordf.length()<8)
                {
                    password.setError("Password must be greater then 8 character");
                    return;
                }
                fAuth.signInWithEmailAndPassword(emailf,passwordf).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"User Created",Toast.LENGTH_SHORT).show();
                            timeToLogin.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            timeToLogin.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, register.class));

            }
        });
    }

}
