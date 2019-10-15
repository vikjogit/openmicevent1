package com.vikjo.openmicevent1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class BusinessHome extends AppCompatActivity {



    Button BtnCreateEvent;
    ListView mBusinessView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_home);


        BtnCreateEvent=findViewById(R.id.createEvent);

        BtnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = (new Intent(BusinessHome.this, CreateEventActivity.class));
                finish();
                startActivity(i);
            }
        });


    }
}
