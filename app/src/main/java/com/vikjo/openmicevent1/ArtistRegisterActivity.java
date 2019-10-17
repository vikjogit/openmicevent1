package com.vikjo.openmicevent1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ArtistRegisterActivity extends AppCompatActivity implements View.OnClickListener {

   private EditText mArtistName;
    private EditText mStageName;
    private EditText mSkills;
    private EditText mPhoneNo;
    private EditText mArtistAge;
    private EditText mArtistEmail;
    private EditText mArtistPassword;
    private String userType = "Artist";


    //Constants
    public static final String CHAT_PREFS = "ChatPrefs";
    public static final String  DISPLAY_ARTIST_NAME_KEY ="Artist Name:";
    public static final String  DISPLAY_STAGE_NAME_KEY ="Artist Stage Name:";
    public static final String  DISPLAY_ARTIST_SKILL_KEY ="Artist Skill:";
    public static final String  DISPLAY_ARTIST_PHONE_NO_KEY ="Artist Phone No:";
    public static final String  DISPLAY_ARTIST_AGE_KEY ="Artist Age:";


    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_register);

        mArtistName = (EditText)findViewById(R.id.editArtistName);
        mStageName = (EditText)findViewById(R.id.editStageName);
        mSkills = (EditText)findViewById(R.id.editSkills);
        mPhoneNo = (EditText)findViewById(R.id.editPhoneNo);
        mArtistAge = (EditText)findViewById(R.id.editArtistAge);
        mArtistEmail=(EditText)findViewById(R.id.editArtistEmail);
        mArtistPassword =(EditText)findViewById(R.id.editArtistPassword);




        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.ArtistSignUp).setOnClickListener(this);





    }






    private void RegisterArtist(){

        final String artistname = mArtistName.getText().toString();
        final String stagename = mStageName.getText().toString();
        final String skills = mSkills.getText().toString();
        final String artistphoneno = mPhoneNo.getText().toString();
        final String artistage = mArtistAge.getText().toString();
        final String artistemail = mArtistEmail.getText().toString();
        final String userType = "Artist";
         String artistpassword = mArtistPassword.getText().toString();

        if(artistname.isEmpty()) {

            mArtistName.setError("Name required");
            mArtistName.requestFocus();
            return;


        }


       else if(skills.isEmpty()) {

            mSkills.setError("Add your Skills!");
            mSkills.requestFocus();
            return;


        }


        else if(artistphoneno.isEmpty()) {

            mPhoneNo.setError("Add your Phone No.!");
            mPhoneNo.requestFocus();
            return;


        }


        else if(artistage.isEmpty()) {

            mArtistName.setError("Add your Age!");
            mArtistName.requestFocus();
            return;


        }


        else if(artistemail.isEmpty()) {

            mArtistEmail.setError("Add your Email!");
            mArtistEmail.requestFocus();
            return;


        }


        else if(artistpassword.isEmpty()) {

            mArtistPassword.setError("Add your Skills!");
            mArtistPassword.requestFocus();
            return;


        }





            //createFirebaseArtist();
        mAuth.createUserWithEmailAndPassword(artistemail,artistpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //we will store additional fields in firebase database

                            Artist artist = new Artist(artistname,stagename,skills,
                                    artistphoneno,artistage,artistemail,userType);





                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(artist).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ArtistRegisterActivity.this,"Registration Succesful",Toast.LENGTH_SHORT).show();


                                    }

                                    else{
                                        Toast.makeText(ArtistRegisterActivity.this,"Try Again",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else{

                            Toast.makeText(ArtistRegisterActivity.this,"Registration Failed. Try Again!",Toast.LENGTH_SHORT).show();



                        }
                    }
                });

















    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ArtistSignUp:
                //SaveArtistInfo();
                RegisterArtist();
                Intent i = (new Intent(ArtistRegisterActivity.this, MainActivity.class));
                finish();
                startActivity(i);
                break;
        }
    }


//private void SaveArtistInfo(){
//
//    String artist_name = mArtistName.getText().toString();
//    String stage_name = mStageName.getText().toString();
//    String artist_skills = mSkills.getText().toString();
//    String artist_phoneno = mPhoneNo.getText().toString();
//    String artist_age = mArtistAge.getText().toString();
//    SharedPreferences prefs = getSharedPreferences(CHAT_PREFS,0);
//    prefs.edit().putString(DISPLAY_ARTIST_NAME_KEY, artist_name).apply();
//    prefs.edit().putString(DISPLAY_STAGE_NAME_KEY, stage_name).apply();
//    prefs.edit().putString(DISPLAY_ARTIST_AGE_KEY, artist_age).apply();
//    prefs.edit().putString(DISPLAY_ARTIST_SKILL_KEY,artist_skills).apply();
//    prefs.edit().putString(DISPLAY_ARTIST_PHONE_NO_KEY,artist_phoneno).apply();
//
//
//}




}




