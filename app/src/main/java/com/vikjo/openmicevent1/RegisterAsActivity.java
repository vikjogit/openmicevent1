package com.vikjo.openmicevent1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterAsActivity extends AppCompatActivity {
    Button BtnRegisterAsArtist;
    Button BtnRegisterAsBusiness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as);

        BtnRegisterAsArtist=findViewById(R.id.artistRegister);
        BtnRegisterAsBusiness=findViewById(R.id.businessRegister);


        BtnRegisterAsArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = (new Intent(RegisterAsActivity.this, ArtistRegisterActivity.class));
                finish();
                startActivity(i);
            }
        });
        BtnRegisterAsBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = (new Intent(RegisterAsActivity.this, BusinessRegisterActivity.class));
                finish();
                startActivity(in);

            }
        });


    }
}
