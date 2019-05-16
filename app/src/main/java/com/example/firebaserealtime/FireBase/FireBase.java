package com.example.firebaserealtime.FireBase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBase {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FireBaseCallBack mFireBaseCallBack;
    private final String HOME = "home";
    private final String LED = "led";
    private final String MOTOR = "motor";
    private final String NAME = "ten";
    private String name;
    private boolean led;
    private int motor;
    public FireBase(FireBaseCallBack fireBaseCallBack) {
        connect();
        this.mFireBaseCallBack = fireBaseCallBack;
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child(NAME).getValue(String.class);
                led = dataSnapshot.child(LED).getValue(Boolean.class);
                motor = dataSnapshot.child(MOTOR).getValue(Integer.class);

                mFireBaseCallBack.onReceiveData(name, led, motor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void connect(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(HOME);
    }

    public void setName(String name){
        myRef.child(NAME).setValue(name);
    }

    public void setLed(boolean led){
        myRef.child(LED).setValue(led);
    }

    public void setMotor(int motor){
        myRef.child(MOTOR).setValue(motor);
    }
}
