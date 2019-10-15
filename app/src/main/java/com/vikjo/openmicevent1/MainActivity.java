package com.vikjo.openmicevent1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

 FirebaseAuth mAuth;
 EditText mEmailView;
 EditText mPasswordView;
 Button BtnSignIn;
 Button BtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

mAuth =FirebaseAuth.getInstance();
mEmailView = (EditText)findViewById(R.id.editEmail);
mPasswordView = (EditText)findViewById(R.id.editPassword);
BtnSignIn=findViewById(R.id.SignInButton);
BtnRegister=findViewById(R.id.RegisterButton);


        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String useremail = mEmailView.getText().toString();
                String userpassword = mPasswordView.getText().toString();

                if(useremail.isEmpty()) {

                    mEmailView.setError("Email required");
                    mEmailView.requestFocus();
                    return;


                }



                else if(userpassword.isEmpty()) {

                    mPasswordView.setError("Password required");
                    mEmailView.requestFocus();
                    return;


                }


                mAuth.signInWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {

                            //if else statements for checking user type.
                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            String RegisteredUserID = currentUser.getUid();

                            DatabaseReference jLoginDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(RegisteredUserID);

                                jLoginDatabase.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String userType = dataSnapshot.child("userType").getValue().toString();
                                        if (userType.equals("Business")) {
                                            Toast.makeText(MainActivity.this,"Business Sign In Succesful",Toast.LENGTH_SHORT).show();
                                            Intent intentBusiness = new Intent(MainActivity.this, BusinessHome.class);
                                            //intentResident.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intentBusiness);
                                            finish();


                                        } else if (userType.equals("Artist")) {
                                            Toast.makeText(MainActivity.this,"Artist Sign In Succesful",Toast.LENGTH_SHORT).show();
                                            Intent intentArtist = new Intent(MainActivity.this, ArtistHome.class);
                                            //intentArtist.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intentArtist);
                                            finish();
                                        }

                                    }

                                            @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {




                                    }
                                });

                        }

                        else {

                            Toast.makeText(MainActivity.this,"Sign In attempt failed, Try again",Toast.LENGTH_SHORT).show();

                        }





                    }
                });






            }
        });





BtnRegister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = (new Intent(MainActivity.this, RegisterAsActivity.class));
        finish();
        startActivity(i);
    }
});

    }
}
