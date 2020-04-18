package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
   // private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$");
    EditText fname, femail, fcontact, fpassword, fre_pass;
    RadioGroup radioGroup;
    Button register;
    TextView click;
    ImageView gmail, google, fb;
    FirebaseAuth fAuth;
    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        femail = (EditText) findViewById(R.id.email);
        fpassword = (EditText) findViewById(R.id.password);
        fname = (EditText) findViewById(R.id.name);
        fcontact = (EditText) findViewById(R.id.contact);
        fre_pass = (EditText) findViewById(R.id.repass);
        google = (ImageView) findViewById(R.id.google);
        gmail = (ImageView) findViewById(R.id.gmail);
        fb = (ImageView) findViewById(R.id.fb);
        click = (TextView) findViewById(R.id.click);
        radioGroup = (RadioGroup) findViewById(R.id.radiosex);
        register = (Button) findViewById(R.id.register);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),login.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = femail.getText().toString().trim();
                String password = fpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    femail.setError("Please Enter email..");
                }
                if (TextUtils.isEmpty(password)) {
                    fpassword.setError("Please enter password....");

                }
                if (password.length() <= 5) {
                    Toast.makeText(getApplicationContext(), "Password must >5 digite", Toast.LENGTH_SHORT).show();
                }
                progress.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registerd Successfully", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(register.this,login.class);
                            startActivity(in);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Registerd failed..",Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                        }
                    }
                });
            }

        });
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(register.this,login.class);
                startActivity(in);
            }
        });
    }
}


