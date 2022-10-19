package com.cms.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class VotingPage extends AppCompatActivity {
    private ConstraintLayout relativeLayout;
    private CardView cardView;
    private AppCompatRadioButton radioBtn;
    private AppCompatButton Votebtn;
    private RadioGroup radioGroupbtn;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_page);
        radioGroupbtn=findViewById(R.id.radiogroup);

        relativeLayout = findViewById(R.id.relativeLayout);
        cardView =  findViewById(R.id.cardView);

        Votebtn=findViewById(R.id.VoteBtn);

        FirebaseAuth auth=FirebaseAuth.getInstance();

        FirebaseUser user=auth.getCurrentUser();
        Votebtn.setOnClickListener(view -> {
            int selectedId = radioGroupbtn.getCheckedRadioButtonId();
            radioBtn =  findViewById(selectedId);
            HashMap<String,String> map=new HashMap<>();
            map.put("candidate",radioBtn.getText().toString());
            map.put("UserID",user.getUid());
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
            Query query=reference.child("CandidateVote").orderByChild("UserID").equalTo(user.getUid());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        Toast.makeText(VotingPage.this, "Already Voted !", Toast.LENGTH_SHORT).show();
                    }else{
                        DatabaseReference  reference1=FirebaseDatabase.getInstance().getReference();
                        reference1.child("CandidateVote")
                                .child(user.getUid())
                                .setValue(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        if(radioBtn.getText().toString().equals("Candidate_one"))
                                        {
                                            FirebaseDatabase databaseonew=FirebaseDatabase.getInstance();
                                            DatabaseReference reference=databaseonew.getReference();
                                            reference.child("CandidateVoteCount")
                                                    .child("Candidate_one")
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            if(snapshot.exists())
                                                            {
                                                                String s=snapshot.child("VoteCount").getValue(String.class);
                                                                voteCount(s,radioBtn.getText().toString());
                                                            }else{
                                                                voteCount("0",radioBtn.getText().toString());
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });

                                        }else if(radioBtn.getText().toString().equals("Candidate_two")){
                                            FirebaseDatabase databaseonew=FirebaseDatabase.getInstance();
                                            DatabaseReference reference=databaseonew.getReference();
                                            reference.child("CandidateVoteCount")
                                                    .child("Candidate_two")
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            if(snapshot.exists())
                                                            {
                                                                String s=snapshot.child("VoteCount").getValue(String.class);
                                                                voteCount(s,radioBtn.getText().toString());
                                                            }else{
                                                                voteCount("0",radioBtn.getText().toString());
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                        }else if(radioBtn.getText().toString().equals("Candidate_three"))
                                        {
                                            FirebaseDatabase databaseonew=FirebaseDatabase.getInstance();
                                            DatabaseReference reference=databaseonew.getReference();
                                            reference.child("CandidateVoteCount")
                                                    .child("Candidate_three")
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            if(snapshot.exists())
                                                            {
                                                                String s=snapshot.child("VoteCount").getValue(String.class);
                                                                voteCount(s,radioBtn.getText().toString());
                                                            }else{
                                                                voteCount("0",radioBtn.getText().toString());
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                        }else{
                                            FirebaseDatabase databaseonew=FirebaseDatabase.getInstance();
                                            DatabaseReference reference=databaseonew.getReference();
                                            reference.child("CandidateVoteCount")
                                                    .child("Candidate_four")
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            if(snapshot.exists())
                                                            {
                                                                String s=snapshot.child("VoteCount").getValue(String.class);
                                                                voteCount(s,radioBtn.getText().toString());
                                                            }else{
                                                                voteCount("0",radioBtn.getText().toString());
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                        }
                                    }
                                });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }
    void voteCount(String s,String Candidatename)
    {
        int count=Integer.parseInt(s);
        count++;
        HashMap<String, String> hashMap=new HashMap<>();
        hashMap.put("Candidatename",Candidatename);
        hashMap.put("VoteCount",String.valueOf(count));
        FirebaseDatabase databaseonew=FirebaseDatabase.getInstance();
        DatabaseReference reference1=databaseonew.getReference();
        reference1.child("CandidateVoteCount")
                .child(Candidatename)
                .setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(VotingPage.this, "Voted Sucessfull", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}