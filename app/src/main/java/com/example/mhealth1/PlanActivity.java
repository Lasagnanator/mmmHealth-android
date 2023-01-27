package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlanActivity extends AppCompatActivity {

    TextView txtNotes;
    Button   btnCallMedico;
    String   PHONENUMBER;

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
                launchPhoneApplication(getIntent().getExtras().getString("id"));
            }
        });
    }

    /**
     * apre il dialer e compone in automatico il numero di telefono
     * @param id String REQUIRE not null
     */
    public void launchPhoneApplication(String id) {
        Intent phone = new Intent(Intent.ACTION_DIAL);
        phone.setData(Uri.parse("tel:"+PHONENUMBER));
        startActivity(phone);
    }

    //#############################################################################################
    //##                                                                                         ##
    //##           Tutta parte da implementare con backend o array di users                      ##
    //##                                                                                         ##
    //#############################################################################################

    /**
     * inserisce il piano medico nella textview
     * @param id String REQUIRE not null
     */
    public void setUserMedicalPlan(String id){
        txtNotes.setText("nulla da segnalare, muori sereno \n - \n - \n - \n - \n - \n - :) \n - ;) \n - :o \n - :( \n a \n b \n c \n d \n e \n f \n g \n h \n i \n l \n m \n n \n o \n p \n quanto \n cazzo \n devo \n scrivere \n per \n provare \n questa \n scrollbar\n ");
        setPhoneNumber(id);
    }

    /**
     * setta il numero del medico curante in base all'id
     * @param id String REQUIRE not null
     */
    public void setPhoneNumber(String id){
        PHONENUMBER = "1234567890";
    }


}