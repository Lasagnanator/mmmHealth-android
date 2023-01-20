package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlanActivity extends AppCompatActivity {

    TextView txtNotes;
    Button   btnCallMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        txtNotes      = findViewById(R.id.txtNotes);
        btnCallMedico = findViewById(R.id.btnCallMedico);
        txtNotes.setBackgroundColor(getResources().getColor(R.color.white));
        txtNotes.setTextColor(getResources().getColor(R.color.black));
        setUserMedicalPlan(getIntent().getExtras().getString("id"));

        btnCallMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lancia app telelfono con numnero del medico
            }
        });
    }

    /**
     * inserisce il piano medico nella textview
     * @param id String REQUIRE not null
     */
    public void setUserMedicalPlan(String id){

        txtNotes.setText("nulla da segnalare, muori sereno \n \n \n \n \n \n a \n b \n c \n d \n e \n quanto \n cazzo \n devo \n scrivere \n per \n provare \n questa \n scrollbar\n ");

    }
}