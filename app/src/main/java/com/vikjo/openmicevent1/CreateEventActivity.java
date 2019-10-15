package com.vikjo.openmicevent1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEventName;
    private EditText mBusinessName;
    private EditText mEventDate;
    private EditText mEventTime;
    private EditText mEventLocation;



    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        mEventName = (EditText)findViewById(R.id.editEventName);
        mBusinessName = (EditText)findViewById(R.id.editCompanyName);
        mEventDate = (EditText)findViewById(R.id.editEventDate);
        mEventTime = (EditText)findViewById(R.id.editEventTime);
        mEventLocation = (EditText)findViewById(R.id.editEventLocation);
        //mArtistEmail=(EditText)findViewById(R.id.editArtistEmail);



        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.CreateEventButton).setOnClickListener(this);




    }







    private void CreateTheEvent(){




        final String eventname = mEventName.getText().toString();
        final String businessname = mBusinessName.getText().toString();
        final String eventdate = mEventDate.getText().toString();
        final String eventtime = mEventTime.getText().toString();
        final String eventlocation = mEventLocation.getText().toString();

        if(eventname.isEmpty()) {

            mEventName.setError("Event Name required");
            mEventName.requestFocus();
            return;


        }


        else if(businessname.isEmpty()) {

            mBusinessName.setError("Add your Business Name");
            mBusinessName.requestFocus();
            return;


        }


        else if(eventdate.isEmpty()) {

            mEventDate.setError("Add the date of the event");
            mEventDate.requestFocus();
            return;


        }


        else if(eventtime.isEmpty()) {

            mEventTime.setError("Add the time for the event");
            mEventTime.requestFocus();
            return;


        }


        else if(eventlocation.isEmpty()) {

            mEventLocation.setError("Add Location of the event");
            mEventLocation.requestFocus();
            return;


        }


        //we will store additional fields in firebase database

        Event event = new Event(eventname,businessname,eventdate,eventtime,eventlocation);





        FirebaseDatabase.getInstance().getReference("Events")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){


                    Toast.makeText(CreateEventActivity.this,"Event Created Succesfully",Toast.LENGTH_SHORT).show();
                    //FirebaseDatabase.getInstance().getReference("Events")
                      //      .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        //    .setValue()

                }

                else{
                    Toast.makeText(CreateEventActivity.this,"Event was not created, Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });
























    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CreateEventButton:
                CreateTheEvent();
                Intent i = (new Intent(CreateEventActivity.this, BusinessHome.class));
                finish();
                startActivity(i);
                break;
        }
    }

}
