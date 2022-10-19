package com.cms.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout linearLayout;
    private AppCompatEditText EmailId;
    private AppCompatEditText Password;
    private AppCompatButton Loginbtn;
    private AppCompatButton signuo;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        linearLayout = findViewById(R.id.linearLayout);
        EmailId =  findViewById(R.id.Email_id);
        Password =  findViewById(R.id.Password);
        Loginbtn =  findViewById(R.id.Loginbtn);
        signuo =findViewById(R.id.signuo);
        auth=FirebaseAuth.getInstance();

        Loginbtn.setOnClickListener(view -> {
            String Email_id= Objects.requireNonNull(EmailId.getText()).toString();
            String password=Objects.requireNonNull(Password.getText()).toString();
           if(Email_id.isEmpty())
           {
               EmailId.setError("Can't Empty");
               EmailId.requestFocus();
           }else if(password.isEmpty()){
               Password.setError("Can't Empty");
               Password.requestFocus();
           }else{
               if(Email_id.equals("admin")&&password.equals("admin")){
                   startActivity(new Intent(MainActivity.this,AdminDashboard.class));
               }else{
                   auth.signInWithEmailAndPassword(Email_id,password)
                           .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if(task.isSuccessful()) {
                                       startActivity(new Intent(MainActivity.this, VotingPage.class));
                                       finish();
                                      }
                                   }
                           }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           });
               }
           }
        });
        signuo.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,SignupScreen.class));
        });

    }
}