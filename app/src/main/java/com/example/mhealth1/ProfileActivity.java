package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView name;
    TextView  lastname;
    TextView  sex;
    TextView  birthdate;
    TextView  height;
    TextView  weight;
    TextView  lastVisit;
    TextView  lastReport;
    ImageView imgAvatar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name       = findViewById(R.id.name);
        lastname   = findViewById(R.id.lastname);
        birthdate  = findViewById(R.id.birthdate);
        sex        = findViewById(R.id.sex);
        height     = findViewById(R.id.height);
        weight     = findViewById(R.id.weight);
        lastVisit  = findViewById(R.id.lastVisit);
        lastReport = findViewById(R.id.lastVisit);
        imgAvatar  = findViewById(R.id.imgAvatar);

        setUserData(getIntent().getExtras().getString("id"));
    }

    /**
     * cerca i paramentri dell'utente in base all'id e setta tutti i dati
     * @param id String REQUIRE not null
     */
    public void setUserData(String id){
        name.setText("Mario");
        lastname.setText("Rossi");
        birthdate.setText("1/1/1990");
        sex.setText("Male");
        height.setText("160cm");
        weight.setText("60kg");
        lastVisit.setText("31/12/2022");
        lastReport.setText("31/2/2000mai");
        if (sex.equals("male")) {
            imgAvatar.getResources().getDrawable(R.drawable.maleavatar);
        } else {
            imgAvatar.getResources().getDrawable(R.drawable.femaleavatar);
        }

    }
}