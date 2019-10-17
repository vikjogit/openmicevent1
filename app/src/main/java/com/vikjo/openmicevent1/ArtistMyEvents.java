package com.vikjo.openmicevent1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class ArtistMyEvents extends AppCompatActivity {
    ListView mArtistMyEventView;
    //private String mBusinessName;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    ArrayList<String> ArtistMyEventlist;
    ArrayAdapter<String> ArtistMyEventadapter;
    EventViewForBusiness eventviewforbusiness;
    DataSnapshot mDataSnapshot;
    //String mBusinessName;
    DatabaseReference myDatabaseRef;
    MyArtistEvent myartistevent;
    Participants participant1;
    String mArtistName;
    String mArtistEventDate;
    String mArtistEventTime;
    String mArtistEventLocation;

    Button ArtistLogOut;
    Button BtnbackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_my_events);

        //eventviewforbusiness = new EventViewForBusiness();
        //myartistevent = new MyArtistEvent();
        participant1 = new Participants();
        mArtistMyEventView = (ListView) findViewById(R.id.MyEventsListView);
        mDatabase = FirebaseDatabase.getInstance();

        ArtistMyEventlist = new ArrayList<>();
        ArtistMyEventadapter = new ArrayAdapter<String>(this,R.layout.listvewitem,R.id.ListViewItem, ArtistMyEventlist);






        SharedPreferences prefsforartistname = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);

        mArtistName = prefsforartistname.getString(MainActivity.DISPLAY_ARTIST_NAME_KEY, null);


        SharedPreferences prefsforartisteventdate = getSharedPreferences(ArtistHome.SHARED_PREFS_FOR_ARTIST_EVENT, MODE_PRIVATE);

        mArtistEventDate = prefsforartisteventdate.getString(ArtistHome.DISPLAY_ARTIST_EVENT_DATE_KEY, null);


        SharedPreferences prefsforartisteventtime = getSharedPreferences(ArtistHome.SHARED_PREFS_FOR_ARTIST_EVENT, MODE_PRIVATE);

        mArtistEventTime = prefsforartisteventtime.getString(ArtistHome.DISPLAY_ARTIST_EVENT_TIME_KEY, null);


        SharedPreferences prefsforartisteventlocation = getSharedPreferences(ArtistHome.SHARED_PREFS_FOR_ARTIST_EVENT, MODE_PRIVATE);

        mArtistEventLocation = prefsforartisteventlocation.getString(ArtistHome.DISPLAY_ARTIST_EVENT_LOCATION_KEY, null);









        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();

        Query mRef = mDatabase.getReference("Event Participants").orderByChild("participantname").equalTo(mArtistName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds1: dataSnapshot.getChildren()) {


                   // String businesseventname = dataSnapshot.child(RegisteredUserID).child("businessname").getValue().toString();

                    //if (businesseventname.equals(mBusinessName))
                    //{

                    participant1 = ds1.getValue(Participants.class);

                    ArtistMyEventlist.add(participant1.getEventnameforparticipants().toString()
                            );

                    //}

                }


                mArtistMyEventView.setAdapter(ArtistMyEventadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        ArtistLogOut = findViewById(R.id.ArtistLogOut2);

        ArtistLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = (new Intent(ArtistMyEvents.this, MainActivity.class));
                finish();
                startActivity(i);
            }
        });



BtnbackButton = findViewById(R.id.BackMyButton);

BtnbackButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent m = (new Intent(ArtistMyEvents.this, ArtistHome.class));
        finish();
        startActivity(m);

    }
});









    }




}
