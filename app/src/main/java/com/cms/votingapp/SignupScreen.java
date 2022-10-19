package com.cms.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class SignupScreen extends AppCompatActivity {
    private AppCompatEditText Votername;
    private AppCompatEditText Voteemail;
    private AppCompatEditText Password;
    private AppCompatEditText confrompassword;
    private AppCompatEditText address;
    private AppCompatButton signup;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        auth=FirebaseAuth.getInstance();


        Votername = findViewById(R.id.Votername);
        Voteemail = findViewById(R.id.Voteemail);
        Password = findViewById(R.id.Password);
        confrompassword = findViewById(R.id.confrompassword);
        address = findViewById(R.id.address);
        signup = findViewById(R.id.appCompatButton);

        signup.setOnClickListener(view -> {
            String Voter_name = Objects.requireNonNull(Votername.getText()).toString();
            String Voter_Email = Objects.requireNonNull(Voteemail.getText()).toString();
            String voter_password = Objects.requireNonNull(Password.getText()).toString();
            String confirm_password = Objects.requireNonNull(confrompassword.getText()).toString();
            String Voter_address = Objects.requireNonNull(address.getText()).toString();

            if (Voter_name.isEmpty()) {
                Votername.setError("Can't Empty");
                Votername.requestFocus();
            } else if (Voter_Email.isEmpty()) {
                Voteemail.setError("Can't Empty");
                Voteemail.requestFocus();
            } else if (voter_password.isEmpty()) {
                Password.setError("Can't Empty");
                Password.requestFocus();
            } else if (confirm_password.isEmpty()) {
                confrompassword.setError("Can't Empty");
                confrompassword.requestFocus();
            } else if (Voter_address.isEmpty()) {
                address.setError("Can't Empty");
                address.requestFocus();
            } else {
                auth.createUserWithEmailAndPassword(Voter_Email, voter_password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                HashMap<String, String> map=new HashMap<>();
                                map.put("Votername",Voter_name);
                                map.put("VoterEmailID",Voter_Email);
                                map.put("VoterPAssword",voter_password);
                                map.put("Voteraddress",Voter_address);
                                    String user= auth.getCurrentUser().getUid();
                                    FirebaseDatabase databaseone=FirebaseDatabase.getInstance();
                                    DatabaseReference database=databaseone.getReference();
                                    database.child(user)
                                            .setValue(map).addOnSuccessListener(unused -> {
                                                Toast.makeText(SignupScreen.this, "Account Created !", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(SignupScreen.this,MainActivity.class));
                                                finish();
                                            });
                                }else{
                                    Toast.makeText(SignupScreen.this, "Signup Error please try again !", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(e -> Toast.makeText(SignupScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show());

            }
        });
    }
}