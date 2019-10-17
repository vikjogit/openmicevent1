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

public class BusinessRegisterActivity extends AppCompatActivity implements  View.OnClickListener {

    private EditText mCompanyName;
    private EditText mLocationView;
    private EditText mEmailView;
    private EditText mPasswordView;
   // private String userType= "Business";


    //public static final String CHAT_PREFS = "ChatPrefs";
    //public static final String DISPLAY_BUSSINESS_NAME_KEY = "Business";
    Button mSignUp;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_register);

        mEmailView = (EditText)findViewById(R.id.edit_email);
        mPasswordView = (EditText)findViewById(R.id.edit_password);
        mCompanyName = (EditText)findViewById(R.id.editCompanyName);
        mLocationView=(EditText)findViewById(R.id.editLocation);

        mAuth =FirebaseAuth.getInstance();

        findViewById(R.id.BusinessSignUp).setOnClickListener(this);









    }

    private void RegisterBusiness(){

        final String businessname = mCompanyName.getText().toString();

        final String businesslocation = mLocationView.getText().toString();


        final String businessemail = mEmailView.getText().toString();
        final String userType = "Business";
        String businesspassword = mPasswordView.getText().toString();

        if(businessname.isEmpty()) {

            mCompanyName.setError("Name required");
            mCompanyName.requestFocus();
            return;


        }










        else if(businessemail.isEmpty()) {

            mEmailView.setError("Add your Email!");
            mEmailView.requestFocus();
            return;


        }


        else if(businesspassword.isEmpty()) {

            mPasswordView.setError("Add your Password!");
            mPasswordView.requestFocus();
            return;


        }
        else if(businesslocation.isEmpty()) {

            mLocationView.setError("Add your Location!");
            mLocationView.requestFocus();
            return;


        }

        //createFirebaseBusiness();
        mAuth.createUserWithEmailAndPassword(businessemail,businesspassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //we will store additional fields in firebase database

                            Business business = new Business(businessname,businesslocation,businessemail,userType);





                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(business).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        Toast.makeText(BusinessRegisterActivity.this,"Registration Succesful",Toast.LENGTH_SHORT).show();


                                    }

                                    else{
                                        Toast.makeText(BusinessRegisterActivity.this,"Try Again",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                        else{

                            Toast.makeText(BusinessRegisterActivity.this,"Registration Failed. Try Again!",Toast.LENGTH_SHORT).show();



                        }
                    }
                });

























    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BusinessSignUp:
                SaveBusinessName();
                RegisterBusiness();

                Intent i = (new Intent(BusinessRegisterActivity.this, MainActivity.class));
                finish();
                startActivity(i);

                break;
        }
    }

private void SaveBusinessName(){

    String displayname = mCompanyName.getText().toString();
    //SharedPreferences prefs = getSharedPreferences(CHAT_PREFS,0);
    //prefs.edit().putString(DISPLAY_BUSSINESS_NAME_KEY, displayname).apply();

}

}
