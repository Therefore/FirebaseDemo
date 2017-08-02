package com.example.nathan.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView MsgText;
    private EditText SendEditText;
    private Button SendButton;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference mChildReference = mRootReference.child("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativeactivity);

        MsgText = (TextView)findViewById(R.id.msgText);
        SendEditText = (EditText) findViewById(R.id.sendEditText);
        SendButton = (Button) findViewById(R.id.sendButton);

        MsgText.setText("Message Appears Here...");
    }

    @Override
    protected void onStart() {
        super.onStart();

        mChildReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String message = dataSnapshot.getValue(String.class);
                MsgText.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void sendMethod(View view){
        mChildReference.setValue(SendEditText.getText().toString());
        SendEditText.setText("");

    }

}
