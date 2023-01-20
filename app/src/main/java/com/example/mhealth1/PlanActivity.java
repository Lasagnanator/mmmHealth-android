package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanActivity extends AppCompatActivity {

    TextView txtNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        txtNotes = findViewById(R.id.txtNotes);
        txtNotes.setBackgroundColor(getResources().getColor(R.color.white));
        txtNotes.setTextColor(getResources().getColor(R.color.black));
        setUserMedicalPlan(getIntent().getExtras().getString("id"));
    }

    /**
     * inserisce il piano medico nella textview
     * @param id String REQUIRE not null
     */
    public void setUserMedicalPlan(String id){

        txtNotes.setText("nulla da segnalare, muori sereno");

    }
}