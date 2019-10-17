package com.vikjo.openmicevent1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArtistHome extends AppCompatActivity {

    ListView mArtistEventView;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    ArrayList<String> Eventlist;
    ArrayAdapter<String> Eventadapter;
    EventViewForArtist eventviewforartist;
    DataSnapshot mDataSnapshot;
    Button BtnMyEvents;
    Button ArtistLogOut;
    //String participantname;
    //String participantage;
    //String participantskills;
    //String participantstagename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_home);

        eventviewforartist = new EventViewForArtist();
        mArtistEventView = (ListView) findViewById(R.id.ArtistEventView);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Events");
        Eventlist = new ArrayList<>();
        Eventadapter = new ArrayAdapter<String>(this,R.layout.listvewitem,R.id.ListViewItem, Eventlist);

        BtnMyEvents = (Button) findViewById(R.id.MyEventsButton);

        ArtistLogOut = (Button) findViewById(R.id.ArtistLogOut1) ;

        BtnMyEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = (new Intent(ArtistHome.this, ArtistMyEvents.class));
                finish();
                startActivity(i);
            }
        });

        ArtistLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = (new Intent(ArtistHome.this, MainActivity.class));
                finish();
                startActivity(i);

            }
        });









        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()) {



                eventviewforartist = ds.getValue(EventViewForArtist.class);

                Eventlist.add(  eventviewforartist.getEventname().toString()
                        //"\n Event Date : "
                        //+eventviewforartist.getEventdate().toString() + "\n Event Location :  "
                          //  +eventviewforartist.getEventlocation() +"\n Event Time : "
                            //    +eventviewforartist.getEventtime() + "\n Cafe Name : "
                //                    +eventviewforartist.getBusinessname()
                );

                }

                mArtistEventView.setAdapter(Eventadapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mArtistEventView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            final String eventnameforparticipants = adapterView.getItemAtPosition(i).toString();

                SharedPreferences prefs = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);

            final String    participantname =prefs.getString(MainActivity.DISPLAY_ARTIST_NAME_KEY, null);

                SharedPreferences prefsforstagename = getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
                final String participantstagename = prefsforstagename.getString(MainActivity.DISPLAY_ARTIST_STAGE_NAME_KEY,null);

                SharedPreferences prefsforskills = getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
                 final String participantskills = prefsforskills.getString(MainActivity.DISPLAY_ARTIST_SKILLS_KEY,null);

                SharedPreferences prefsforage = getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
                final String participantage = prefsforage.getString(MainActivity.DISPLAY_ARTIST_AGE_KEY,null);

                Participants participants = new Participants(eventnameforparticipants, participantname,participantstagename, participantage, participantskills);


                FirebaseDatabase.getInstance().getReference("Event Participants")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(participants).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            //ADD SHARED PREFERENCES FOR EVENT TIME, DATE, VENUE




                            Toast.makeText(ArtistHome.this,"Participated in event succesfully!",Toast.LENGTH_SHORT).show();


                        }

                        else{
                            Toast.makeText(ArtistHome.this,"Participation Unsuccesful, Try Again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });






    }









}
