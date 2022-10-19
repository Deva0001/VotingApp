package com.cms.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDashboard extends AppCompatActivity {
    private AppCompatTextView candidateOnecount;
    private AppCompatTextView candidateTwocount;
    private AppCompatTextView candidateThreecount;
    private AppCompatTextView candidateFourcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        candidateOnecount =  findViewById(R.id.candidate_onecount);
        candidateTwocount =  findViewById(R.id.candidate_twocount);
        candidateThreecount =findViewById(R.id.candidate_threecount);
        candidateFourcount = findViewById(R.id.candidate_fourcount);

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
                            candidateOnecount.setText(s);
                        }else{
                            candidateOnecount.setText("0");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        FirebaseDatabase databaseonetwo=FirebaseDatabase.getInstance();
        DatabaseReference referencetwo=databaseonetwo.getReference();
        referencetwo.child("CandidateVoteCount")
                .child("Candidate_two")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            String s=snapshot.child("VoteCount").getValue(String.class);
                            candidateTwocount.setText(s);
                        }else{
                            candidateTwocount.setText("0");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        FirebaseDatabase databaseonthree=FirebaseDatabase.getInstance();
        DatabaseReference referencethree=databaseonthree.getReference();
        referencethree.child("CandidateVoteCount")
                .child("Candidate_three")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            String s=snapshot.child("VoteCount").getValue(String.class);
                            candidateThreecount.setText(s);
                        }else{
                            candidateThreecount.setText("0");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        FirebaseDatabase databaseonewfour=FirebaseDatabase.getInstance();
        DatabaseReference referencefour=databaseonewfour.getReference();
        referencefour.child("CandidateVoteCount")
                .child("Candidate_four")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            String s=snapshot.child("VoteCount").getValue(String.class);
                            candidateFourcount.setText(s);
                        }else{
                            candidateFourcount.setText("0");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}