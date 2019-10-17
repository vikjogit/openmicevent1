package com.vikjo.openmicevent1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BusinessHome extends AppCompatActivity {


    ListView mBusinessEventView;
    //private String mBusinessName;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    ArrayList<String> BusinessEventlist;
    ArrayAdapter<String> BusinessEventadapter;
    EventViewForBusiness eventviewforbusiness;
    DataSnapshot mDataSnapshot;
    String mBusinessName;
    DatabaseReference myDatabaseRef;
    public static final String SHARED_PREFS_FOR_EVENT = "SharedPrefs";
    public static final String DISPLAY_EVENT_NAME_KEY = "EventName";





    Button BtnCreateEvent;
    Button BtnBusinessLogOut;
    Button BtnViewParticipants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);



        eventviewforbusiness = new EventViewForBusiness();
        mBusinessEventView = (ListView) findViewById(R.id.BusinessEventView);
        mDatabase = FirebaseDatabase.getInstance();

        BusinessEventlist = new ArrayList<>();
        BusinessEventadapter = new ArrayAdapter<String>(this,R.layout.listvewitem,R.id.ListViewItem, BusinessEventlist);


        BtnCreateEvent=findViewById(R.id.createEvent);

        BtnBusinessLogOut = findViewById(R.id.BusinessLogOut1);

        BtnViewParticipants = findViewById(R.id.ParticipantView);


        //final String businesseventname = mDataSnapshot.child("businessname").getValue().toString();

        SharedPreferences prefsforbusinessname = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);

        mBusinessName = prefsforbusinessname.getString(MainActivity.DISPLAY_BUSINESS_NAME_KEY, null);



        Query mRef = mDatabase.getReference("Events").orderByChild("businessname").equalTo(mBusinessName);
        //final String businesseventname = mRef.

        //DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Events").child(RegisteredUserID);



        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for(DataSnapshot ds: dataSnapshot.getChildren()) {




                        eventviewforbusiness = ds.getValue(EventViewForBusiness.class);



                        BusinessEventlist.add(eventviewforbusiness.getEventname().toString() + " \n  "
                                + eventviewforbusiness.getEventdate().toString());

                    //eventviewforbusiness = ds.getValue(EventViewForBusiness.class);
                    String event_name = eventviewforbusiness.getEventname().toString();
                    SharedPreferences prefsforeventname = getSharedPreferences(SHARED_PREFS_FOR_EVENT,MODE_PRIVATE);

                    prefsforeventname.edit().putString(DISPLAY_EVENT_NAME_KEY, event_name).apply();



                    }


                mBusinessEventView.setAdapter(BusinessEventadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });










        BtnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = (new Intent(BusinessHome.this, CreateEventActivity.class));
                finish();
                startActivity(i);
            }
        });

        BtnBusinessLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = (new Intent(BusinessHome.this, MainActivity.class));
                finish();
                startActivity(i);

            }
        });


        BtnViewParticipants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = (new Intent(BusinessHome.this,BusinessViewParticipants.class));
                finish();
                startActivity(n);
            }
        });




    }

//private void SetupBusinessName(){

  //  SharedPreferences prefs = getSharedPreferences(BusinessRegisterActivity.CHAT_PREFS, MODE_PRIVATE);

    //mBusinessName =prefs.getString(BusinessRegisterActivity.DISPLAY_BUSSINESS_NAME_KEY, null);

    //if(mBusinessName == null) mBusinessName = "Anonymous";



//}




}


