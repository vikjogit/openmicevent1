package com.vikjo.openmicevent1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BusinessViewParticipants extends AppCompatActivity {

    ListView mParticipantForEventView;
    //private String mBusinessName;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    ArrayList<String> BusinessParticipantlist;
    ArrayAdapter<String> BusinessParticipantadapter;
    //EventViewForBusiness eventviewforbusiness;
    DataSnapshot mDataSnapshot;
    //String mBusinessName;
    DatabaseReference myDatabaseRef;
    Participants participant2;
    //Participants participant3;
    public static final String SHARED_PREFS_FOR_PARTICIPANT = "SharedPrefs";
    public static final String DISPLAY_PARTICIPANT_NAME_KEY = "Business Name";


    String mEventName;

    Button BtnBusinessLogOut;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_view_participants);


        participant2 = new Participants();

        mParticipantForEventView = (ListView) findViewById(R.id.BusinessListParticipants);
        mDatabase = FirebaseDatabase.getInstance();
        BtnBusinessLogOut = findViewById(R.id.BusinessLogOut2);
        BusinessParticipantlist = new ArrayList<>();
        BusinessParticipantadapter = new ArrayAdapter<String>(this,R.layout.listvewitem,R.id.ListViewItem, BusinessParticipantlist);






        SharedPreferences prefsforbsuinesseventname = getSharedPreferences(BusinessHome.SHARED_PREFS_FOR_EVENT, MODE_PRIVATE);

        mEventName= prefsforbsuinesseventname.getString(BusinessHome.DISPLAY_EVENT_NAME_KEY, null);



        Query mRef = mDatabase.getReference("Event Participants").orderByChild("eventnameforparticipants").equalTo(mEventName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds1: dataSnapshot.getChildren()) {
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    final String RegisteredUserID = currentUser.getUid();

                    // String businesseventname = dataSnapshot.child(RegisteredUserID).child("businessname").getValue().toString();

                    //if (businesseventname.equals(mBusinessName))
                    //{

                    participant2 = ds1.getValue(Participants.class);


                    BusinessParticipantlist.add(participant2.getParticipantname().toString() + " \n  "

                    );
                    String mParticipantName = participant2.getParticipantname().toString();
                    SharedPreferences prefsforparticipantname = getSharedPreferences(SHARED_PREFS_FOR_PARTICIPANT,MODE_PRIVATE);
                    prefsforparticipantname.edit().putString(DISPLAY_PARTICIPANT_NAME_KEY, mParticipantName).apply();

                    //}

                }


                mParticipantForEventView.setAdapter(BusinessParticipantadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        BtnBusinessLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BusinessViewParticipants.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });



    mParticipantForEventView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            SharedPreferences prefsforparticipantname2 = getSharedPreferences(BusinessViewParticipants.SHARED_PREFS_FOR_PARTICIPANT,MODE_PRIVATE);
            final String participantname2 = prefsforparticipantname2.getString(BusinessViewParticipants.DISPLAY_PARTICIPANT_NAME_KEY,null);



            final String    participantname = null;
            final String eventnameforparticipants = null;
            final String participantstagename = null;
            final String participantskills = null;
            final String participantage = null;

            Participants participant3 = new Participants(eventnameforparticipants, participantname,participantstagename, participantage, participantskills);



            //participant3 = new Participants();

            //DatabaseReference DataRef =FirebaseDatabase.getInstance().getReference("Event Participants").orderByChild();

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            Query deletionQuery = ref.child("Event Participants").orderByChild("participantname").equalTo(participantname2);

            //.orderByChild("participantname");
                    //.equalTo(participantname2);

            deletionQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot participantsnapshot: dataSnapshot.getChildren())
                    {
                        participantsnapshot.getRef().removeValue();

                        Toast.makeText(BusinessViewParticipants.this,"Participant Deleted succesfully",Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("OPENMICEVENT1", "onCancelled", databaseError.toException());

                }
            });



//            DataRef.setValue(participant3).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//
//
//
//                    if(task.isSuccessful()){
//
//                        //ADD SHARED PREFERENCES FOR EVENT TIME, DATE, VENUE
//
//
//
//
//                        Toast.makeText(BusinessViewParticipants.this,"DELETED SUCCESFULLY!",Toast.LENGTH_SHORT).show();
//                        Intent n = (new Intent(BusinessViewParticipants.this, BusinessHome.class));
//                        finish();
//                        startActivity(n);
//
//                    }
//
//                    else{
//                        Toast.makeText(BusinessViewParticipants.this,"Deletion Unsuccesful, Try Again",Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            });



        }
    });


    }
}
