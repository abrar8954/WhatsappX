package com.abrarsardar.whatsappa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.abrarsardar.whatsappa.Models.Users;
import com.abrarsardar.whatsappa.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth Auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("We are creating your account");

        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                Auth.createUserWithEmailAndPassword
                        (binding.etEmail.getText().toString(), binding.etPassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull  Task<AuthResult> task) {

//                                FirebaseUser currentUser = Auth.getCurrentUser();
//                                if(currentUser != null){
//                                    currentUser.reload();
//                                }

                                if(task.isSuccessful()){
                                          Users users = new Users(binding.etUserName.getText().toString(), binding.etEmail.getText().toString(),
                                                  binding.etPassword.getText().toString(), binding.etcountry.getText().toString(), binding.etcity.getText().toString());

                                          String id = task.getResult().getUser().getUid();
                                          database.getReference().child("Users").child(id).setValue(users);
                                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                    intent.putExtra("etcountry", binding.etcountry.getText().toString());
                                    startActivity(intent);
                                    Toast.makeText(SignupActivity.this, "Account Created Successfully...", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        binding.tvAlreadyaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }
}