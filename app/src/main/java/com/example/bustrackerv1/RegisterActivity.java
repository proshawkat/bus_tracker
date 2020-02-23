package com.example.bustrackerv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFullName, etMobileNo, etEmail, etPassword, etConPassword, etbusNumber;
    private Button registerBtn;
    private DatabaseReference reff;
    private Drivers drivers;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName      =  (EditText)findViewById(R.id.etFullName);
        etEmail         =  (EditText)findViewById(R.id.etEmail);
        etMobileNo      =  (EditText)findViewById(R.id.etMobileNo);
        etPassword      =  (EditText)findViewById(R.id.password);
        etConPassword   =  (EditText)findViewById(R.id.confirmPassword);
        etbusNumber       =  (EditText)findViewById(R.id.busNumber);
        registerBtn     =  (Button)findViewById(R.id.registerBtn);

        drivers = new Drivers();
        reff = FirebaseDatabase.getInstance().getReference().child("Drivers");
        mAuth = FirebaseAuth.getInstance();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullName = etFullName.getText().toString().trim();
                final String email = etEmail.getText().toString().trim();
                final String phone = etMobileNo.getText().toString().trim();
                final String busNumber = etbusNumber.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();


                if (TextUtils.isEmpty(fullName)) {
                    Toast.makeText(getApplicationContext(), "Enter your full name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(busNumber)) {
                    Toast.makeText(getApplicationContext(), "Enter Bus Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 5 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Drivers information = new Drivers(fullName, email, phone, password, busNumber);

                            FirebaseDatabase.getInstance().getReference("Drivers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(information);
                            Toast.makeText(RegisterActivity.this,"Data inserted successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }

}
